package adventure.model.adventure;

import adventure.model.*;
import adventure.model.stats.CardStatTracker;
import adventure.model.stats.CardStats;
import adventure.model.stats.AdvMatchResult;
import adventure.model.target.*;
import adventure.model.target.base.AdvCard;
import adventure.model.target.base.AdvCardList;
import adventure.model.target.base.AdvLocation;
import adventure.model.target.base.AdvLocationList;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.logger.MLogger;
import snapMain.model.target.*;

import java.util.*;

public class Adventure {

    String profileName;
    AdvProfile profile;
    AdventureDatabase adventureDatabase;
    AdventureSaver saver;
    AdventureLoader loader;
    Team team;
    AdvCardList availableBosses;
    AdvLocationList availableLocations;
    WorldList worlds;
    int currentWorldNum;
    boolean newProfileCheck;
    CardStatTracker cardStatTracker;
    ExhaustionCalculator exhaustionCalculator = new ExhaustionCalculator();
    MIACardTracker miaCardTracker;
    DeckProfileList deckProfiles;
    List<InfinityStone> allInfinityStones;
    Difficulty difficulty;
    AdvTimekeeper timekeeper;

    MLogger logger = new MLogger(Adventure.class);

    public Adventure(AdvMainDatabase mainDB, AdvProfile profile) {
        //Initialize base objects
        team = new Team();
        availableBosses = new AdvCardList(new ArrayList<>());
        availableLocations = new AdvLocationList(new ArrayList<>());
        worlds = new WorldList(new ArrayList<>());
        this.profile = profile;
        adventureDatabase = new AdventureDatabase();
        newProfileCheck = false;
        allInfinityStones = new ArrayList<>();
        cardStatTracker = new CardStatTracker();
        miaCardTracker = new MIACardTracker();
        deckProfiles = new DeckProfileList(SnapMainConstants.DECK_PROFILE_DEFAULT);
        timekeeper = new AdvTimekeeper();
        createInfinityStones();
        loadAdventure(mainDB);
    }

    public void initialize(AdvMainDatabase db, int numTeamMembers, int numTeamCaptains, Difficulty d) {
        difficulty = d;
        generateAdventure(db, numTeamMembers, numTeamCaptains);
    }

    public List<String> convertToString() {
        List<String> adventureString = new ArrayList<>();
        adventureString.add(profileName);
        adventureString.add(currentWorldNum + "");
        adventureString.add(team.toSaveString());
        adventureString.add(availableBosses.toSaveString());
        adventureString.add(availableLocations.toSaveString());
        adventureString.add(worlds.toSaveString());
        adventureString.add(cardStatTracker.toSaveString());
        adventureString.add(deckProfiles.toSaveString());
        adventureString.add(difficulty.toString());
        adventureString.add(adventureDatabase.toSaveString());
        adventureString.add(miaCardTracker.toSaveString());
        adventureString.add(timekeeper.toSaveString());
        return adventureString;
    }

    public void convertFromString(AdvMainDatabase mainDB, List<String> stringToConvert) {
        if (stringToConvert.isEmpty()) {
            return;
        }

        String[] splitString = stringToConvert.get(0).split(SnapMainConstants.CSV_SEPARATOR);

        profileName = splitString[0];
        currentWorldNum = Integer.parseInt(splitString[1]);
        adventureDatabase.fromSaveString(splitString[9], mainDB);
        team.convertFromString(splitString[2], adventureDatabase);
        availableBosses.fromSaveString(splitString[3], adventureDatabase.getAdvCards());
        availableLocations.fromSaveString(splitString[4], adventureDatabase.getSections());
        worlds.fromSaveString(adventureDatabase, mainDB, splitString[5]);
        cardStatTracker.fromSaveString(splitString[6]);
        deckProfiles.fromSaveString(splitString[7], adventureDatabase);
        difficulty = Difficulty.valueOf(splitString[8]);
        miaCardTracker.fromSaveString(splitString[10], team.getAllCards());
        timekeeper.fromSaveString(splitString[11]);
    }

    public void saveAdventure() {
        saver = new AdventureSaver(profile.getProfileFile(), this);
        saver.writeFile();
    }

    private void loadAdventure(AdvMainDatabase db) {
        loader = new AdventureLoader(profile.getProfileFile());
        List<String> adventureFile = loader.readFile();
        List<String> adventureString = new ArrayList<>(adventureFile);
        convertFromString(db, adventureString);
    }

    private void generateAdventure(AdvMainDatabase db, int numTeamMembers, int numCaptains) {
        adventureDatabase.createNewDatabase(db);
        availableBosses = new AdvCardList(adventureDatabase.getAdvCards());
        availableLocations = new AdvLocationList(adventureDatabase.getSections());
        team = new Team(adventureDatabase, numTeamMembers, numCaptains);
        worlds = new WorldList(adventureDatabase);
        availableBosses.removeAll(worlds.getAllBosses());
        availableLocations.removeAll(worlds.getAllLocations());
        currentWorldNum = 1;
        cardStatTracker.initialize(getAllCards());
        World w = worlds.get(currentWorldNum);
        w.initialize(getFreeAgents());
        placeInfinityStones();
    }

    private void createInfinityStones() {
        for (InfinityStoneID id : InfinityStoneID.values()) {
            InfinityStone infinityStone = new InfinityStone(id.getID(), id);
            allInfinityStones.add(infinityStone);
        }
    }

    private void placeInfinityStones() {

        List<Integer> possibleSections = new ArrayList<>();
        for (int i = 0; i < AdventureConstants.NUMBER_OF_WORLDS * AdventureConstants.SECTIONS_PER_WORLD; i++) {
            possibleSections.add(i);
        }
        Collections.shuffle(possibleSections);
        for (int i = 0; i < allInfinityStones.size(); i++) {
            int choice = possibleSections.get(i);
            int worldNum = (int) Math.floor((double) choice / AdventureConstants.SECTIONS_PER_WORLD) + 1;
            int secNum = choice % AdventureConstants.SECTIONS_PER_WORLD + 1;
            World w = worlds.get(worldNum);
            Section s = w.getSection(secNum);
            s.addReward(allInfinityStones.get(i));
        }
    }

    public World getCurrentWorld() {
        return worlds.get(currentWorldNum);
    }

    public int getCurrentSectionNum() {
        return getCurrentWorld().getCurrentSectionNum();
    }

    public Team getTeam() {
        return team;
    }

    public String getProfileName() {
        return profileName;
    }
    public AdvProfile getProfile() {
        return profile;
    }

    public boolean isNewProfile() {
        return newProfileCheck;
    }

    public void setProfileName(String p) {
        profileName = p;
    }

    public void setNewProfile(boolean b) {
        newProfileCheck = b;
    }

    public void sendAway(int worldNum, ActiveCard card) {
        miaCardTracker.addCard(worldNum, card);
        team.sendAway(card);
    }

    private int getFutureWorldNum() {
        Random random = new Random();
        int worldChosen = random.nextInt(getCurrentWorldNum()+1,worlds.size()) + 1;
        return worldChosen;
    }


    public void completeCurrentSection() {
        getCurrentWorld().clearSection(getCurrentSectionNum());
        if (getCurrentWorld().numClearedSections() >= difficulty.getSectionsRequiredToClear()) {
            getCurrentWorld().setBossRevealed(true);
        }
        getCurrentWorld().revealNextSection(getCurrentSectionNum());
        getCurrentWorld().incrementCurrentSectionNum();
        logInfo("Section " + getCurrentWorldNum() + "-" + getCurrentSectionNum()
                + " completed");
    }
    public void skipCurrentSection() {
        getCurrentWorld().skipCurrentSection();
        logInfo("Section " + getCurrentWorldNum() + "-" + getCurrentSectionNum()
                + " skipped");
    }

    public void completeCurrentWorld() {
        team.exhaustedCardsRecover();
        reclaimCards();

        team.tempCardsExpire();
        if (currentWorldNum < AdventureConstants.NUMBER_OF_WORLDS) {
            currentWorldNum++;
            getCurrentWorld().initialize(team.getFreeAgents());
        } else if (currentWorldNum == AdventureConstants.NUMBER_OF_WORLDS && allInfinityStones.size() == 6) {
            currentWorldNum++;
            worlds.add(new World(adventureDatabase));
        }
        logInfo("World" + getCurrentWorldNum()
                + " completed!");
    }

    public ActiveCardList getActiveCards() {
        ActiveCardList active = new ActiveCardList(new ArrayList<>());
        active.addAll(team.getTeamCards().getActiveThings());
        active.addAll(team.getTempCards().getActiveThings());
        return active;
    }

    public ActiveCardList getTeamAndTempCards() {
        ActiveCardList active = new ActiveCardList(new ArrayList<>());
        active.addAll(team.getTeamCards().getThings());
        active.addAll(team.getTempCards().getThings());
        return active;
    }

    public ActiveCardList getTeamCards() {
        return team.getTeamCards();
    }

    public ActiveCardList getStationedCards() {
        ActiveCardList stationedCards = new ActiveCardList(new ArrayList<>());
        for (Section s : getCurrentWorld().getSections()) {
            stationedCards.addAll(s.getStationedCards().getThings());
        }
        return stationedCards;
    }

    //Normal method for drafting cards
    public TargetList<ActiveCard> draftCards() {
        ActiveCardList cards = new ActiveCardList(new ArrayList<>());
        ActiveCardList freeAgents = new ActiveCardList(team.getFreeAgents());
        freeAgents.shuffle();
        cards.addAll(freeAgents.subList(0, AdventureConstants.NUM_DRAFT_CARDS));
        return cards;
    }

    //Draft specific cards from a predefined subset
    public TargetList<ActiveCard> draftCards(TargetList<ActiveCard> subset) {
        ActiveCardList cards = new ActiveCardList(new ArrayList<>());
        ActiveCardList freeAgents = new ActiveCardList(subset.getThings());
        freeAgents.shuffle();
        cards.addAll(freeAgents.subList(0, AdventureConstants.NUM_DRAFT_CARDS));
        return cards;
    }

    public void addFreeAgentToTeam(ActiveCard card) {
        team.getTeamCards().add(card);
        team.getFreeAgents().remove(card);
        MLogger.LOGGER.info("Drafted " + card + " to team.");
    }

    public void healCard(ActiveCard card) {
        team.healCard(card);
    }

    public TargetList<ActiveCard> getWoundedCards() {
        return team.getWoundedCards();
    }

    public void stationCard(Section s, ActiveCard card) {
        team.stationCard(s, card);
    }

    public void addFreeAgentToTemp(ActiveCard card) {
        team.getTempCards().add(card);
        team.getFreeAgents().remove(card);
        card.setTemp(true);
        logInfo("Drafted " + card + " to temp.");
    }

    public ActiveCardList getFreeAgents() {
        return team.getFreeAgents();
    }

    public void collectPickups(Section section) {
        TargetList<Playable> pickups = section.getRewards();
        for (Playable p : pickups) {
            if (p instanceof ActiveCard) {
                team.addCardToTeam((ActiveCard) p);
                team.getMIACards().remove((ActiveCard) p);
            } else if (p instanceof InfinityStone) {
                team.gainInfinityStone((InfinityStone) p);
            }
        }
        pickups.clear();
    }

    public AdvLocationList getAvailableLocations() {
        return availableLocations;
    }

    //Do not add changed locations back to the available locations pool since this would make locations like Westview
    //and Mirror Dimension infinitely recycle.
    public void updateSection(AdvLocation newLoc, int sectionNum) {
        World w = getCurrentWorld();
        w.updateSection(newLoc, sectionNum);
        if (newLoc.isActualThing())
            availableLocations.remove(newLoc);
    }

    public void destroySection(int sectionNum) {
        World w = getCurrentWorld();
        AdvLocation oldLoc = w.getSection(sectionNum).getLocation();
        w.updateSection(new AdvLocation(new Location()), sectionNum);
    }

    public void updateStats(ActiveCardList deck, AdvMatchResult result) {
        cardStatTracker.updateCardStats(deck, result);
        getCurrentWorld().updateWorldStats(result);
    }

    public void addCardToTeam(ActiveCard card) {
        if (card != null) {
            team.getFreeAgents().remove(card);
            team.getTeamCards().add(card);
        }
    }
    public Card getBossCard() {
        World w = getCurrentWorld();
        AdvCard boss = (AdvCard) w.getBoss().getSubject();
        return boss.getCard();
    }

    public int getTotalMatchCount() {
        int matchCount = 0;
        for (World w : worlds) {
            matchCount += w.getMatchCount();
        }
        return matchCount;
    }

    public int getNumMatchType(AdvMatchResult m) {
        return getCurrentWorld().getNumMatchType(m);
    }

    public int getWorldMatchCount() {
        return getCurrentWorld().getMatchCount();
    }

    public DeckProfileList getDeckProfiles() {
        return deckProfiles;
    }

    public void updateDeckProfiles(DeckProfileList list, int latest) {
        deckProfiles = list;
        deckProfiles.setLatestProfileNum(latest);
    }

    public Enemy replaceEnemy(Playable p, int sectionNum, boolean clone) {
        Section s = getCurrentWorld().getSection(sectionNum);
        Enemy enemy;
        if (s instanceof BossSection)
            enemy = new Enemy(p, getCurrentWorld().calculateBossBonus());
        else
            enemy = new Enemy(p, getCurrentWorld().calculateMookBonus());
        SnapTarget oldEnemy = s.replaceEnemy(enemy);

        if (oldEnemy instanceof AdvCard) {
            Card oldEnemyCard = ((AdvCard) oldEnemy).getCard();
            if (oldEnemyCard.getID() != SnapMainConstants.NO_ICON_ID && !clone)
                team.addCardToFreeAgents(new ActiveCard(oldEnemyCard));
        }
        if (p instanceof AdvCard && !clone) {
            Card enemyCard = ((AdvCard) p).getCard();
            team.removeFromFreeAgents(enemyCard);
        }
        return enemy;

    }

    public AdventureDatabase getAdventureDatabase() {
        return adventureDatabase;
    }

    public boolean reclaimCard(ActiveCard card) {
        boolean reclaimed = false;
        for (Section s : getCurrentWorld().getSections()) {
            if (s.getStationedCards().contains(card)) {
                reclaimed = s.removeStationedCard(card, team);
            }
        }
        if (!reclaimed) {
            reclaimed = team.returnCard(card);
            miaCardTracker.removeCard(card);
        }
        return reclaimed;
    }


    public void addFreeAgentsToTeam(ActiveCardList cards) {
        for (ActiveCard c : cards) {
            addFreeAgentToTeam(c);
        }
    }

    public void addFreeAgentsToTemp(ActiveCardList cards) {
        for (ActiveCard c : cards) {
            addFreeAgentToTemp(c);
        }
    }

    public ActiveCardList lookupActiveCards(CardList lookupCards) {
        ActiveCardList lookedUpCards = new ActiveCardList();
        ActiveCardList activeCards = getAllCards();
        for (Card c : lookupCards) {
            ActiveCard a = activeCards.lookupActiveCard(c.getID());
            lookedUpCards.add(a);
        }
        return lookedUpCards;
    }

    private ActiveCardList getAllCards() {
        return team.getAllCards();
    }

    public ActiveCard lookupCard(int id) {
        ActiveCardList allCards = getAllCards();
        return allCards.lookupActiveCard(id);
    }

    public ActiveCardList exhaustCards(ActiveCardList deck) {
        ActiveCardList newlyExhaustedCards = new ActiveCardList();
        Map<ActiveCard, Boolean> exhaustionMap =
                exhaustionCalculator.checkExhaustion(getActiveCards(), cardStatTracker);
        for (Map.Entry<ActiveCard, Boolean> e : exhaustionMap.entrySet()) {
            e.getKey().setStatus(StatusEffect.EXHAUSTED, e.getValue());
            if (e.getValue() && deck.contains(e.getKey()))
                newlyExhaustedCards.add(e.getKey());
        }
        return newlyExhaustedCards;
    }

    public ActiveCardList recoverExhaustedCards(ActiveCardList deck) {
        ActiveCardList recovered = new ActiveCardList();
        for (ActiveCard c : getTeamAndTempCards()) {
            if (c.hasStatus(StatusEffect.EXHAUSTED) && !deck.contains(c)) {
                c.setStatus(StatusEffect.EXHAUSTED, false);
                recovered.add(c);
            }
        }
        return recovered;
    }

    public int getCurrentWorldNum() {
        return getCurrentWorld().getWorldNum();
    }


    public ActiveCardList reclaimCards() {
        ActiveCardList reclaimedCards = new ActiveCardList();
        reclaimedCards.addAll(team.retrieveMIACards(miaCardTracker, getCurrentWorldNum() + 1).getThings());
        reclaimedCards.addAll(team.retrieveCapturedCards().getThings());
        reclaimedCards.addAll(getCurrentWorld().retrieveStationedCards());
        return reclaimedCards;
    }

    public Section enemyEscapes(int sectionNum) {
       Enemy enemy =  worlds.get(getCurrentWorldNum()).enemyEscapes(sectionNum);
       int futureWorld = getFutureWorldNum();
       World world = worlds.get(futureWorld);
       Section escapedSection = world.getRandomSection();
       escapedSection.setEnemy(enemy);
       team.sendCapturedCardsAway(miaCardTracker, futureWorld+1);
       logger.info(enemy + " escaped from world " + getCurrentWorldNum());
       return escapedSection;
    }

    public boolean failStateCheck() {
        ActiveCardList teamCards = getTeamCards();
        ActiveCardList activeCards = getActiveCards();
        return activeCards.size() < SnapMainConstants.DECK_SIZE || teamCards.hasNoCaptains();
    }

    private void createFinalWorld() {
        if(worlds.size() == AdventureConstants.NUMBER_OF_WORLDS)
        {
            worlds.add(new FinalWorld(adventureDatabase, team.getFreeAgents(), availableLocations));
        }
    }

    public Enemy getFinalBoss() {
        if(worlds.size() == AdventureConstants.NUMBER_OF_WORLDS)
            createFinalWorld();
        return worlds.get(AdventureConstants.NUMBER_OF_WORLDS).getBoss();
    }

    public Map<Integer, CardStats> getRankedCardStats() {
        return cardStatTracker.getCardStatsSorted();
    }

    public void captureCard(ActiveCard c) {
        team.captureCard(c);
        logInfo(c + " captured");
    }

    public void logInfo(String string)
    {
        logger.info(string);
    }

    public void stealRandomInfinityStone(Section s) {
        Section escapedSection = enemyEscapes(s.getSectionNum());
        InfinityStone randomInfinityStone = team.getRandomInfinityStone();
        escapedSection.addReward(randomInfinityStone);
        team.loseInfinityStone(randomInfinityStone);
        logger.info(randomInfinityStone + " stolen by " + escapedSection.getEnemy());
    }

    public boolean hasInfinityStone() {
        return !allInfinityStones.isEmpty();
    }

    public boolean hasAvailableSkips() {
        return getCurrentWorld().getAvailableSkips() > 0;
    }

    public String getTotalPlayTime() {
        return timekeeper.getTotalPlayTime();
    }

    public String getCurrentWorldPlayTime() {
        return getCurrentWorld().getWorldPlayTimeString();
    }
}
