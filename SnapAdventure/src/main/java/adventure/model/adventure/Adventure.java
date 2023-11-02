package adventure.model.adventure;

import adventure.model.*;
import adventure.model.stats.AdvMatchResult;
import adventure.model.stats.CardStatTracker;
import adventure.model.stats.CardStats;
import adventure.model.target.*;
import adventure.model.target.base.*;
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
    int numberOfWorlds;
    int currentWorldNum;
    boolean newProfileCheck;
    CardStatTracker cardStatTracker;
    ExhaustionCalculator exhaustionCalculator = new ExhaustionCalculator();
    MIACardTracker miaCardTracker;
    DeckProfileList deckProfiles;
    List<InfinityStone> allInfinityStones;
    Difficulty difficulty;
    AdvTimekeeper timekeeper;
    boolean finalWorldUnlocked;

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
        finalWorldUnlocked = false;
        createInfinityStones();
        loadAdventure(mainDB);
    }

    public void initialize(AdvMainDatabase db, int numTeamMembers, int numTeamCaptains, int numWorlds, Difficulty d) {
        difficulty = d;
        generateAdventure(db, numTeamMembers, numTeamCaptains, numWorlds);
    }

    public List<String> convertToString() {
        List<String> adventureString = new ArrayList<>();
        adventureString.add(profileName);
        adventureString.add(currentWorldNum + "");
        adventureString.add(numberOfWorlds + "");
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
        adventureString.add(finalWorldUnlocked + "");
        return adventureString;
    }

    public void convertFromString(AdvMainDatabase mainDB, List<String> stringToConvert) {
        if (stringToConvert.isEmpty()) {
            return;
        }

        String[] splitString = stringToConvert.get(0).split(SnapMainConstants.CSV_SEPARATOR);

        profileName = splitString[0];
        currentWorldNum = Integer.parseInt(splitString[1]);
        numberOfWorlds = Integer.parseInt(splitString[2]);
        adventureDatabase.fromSaveString(splitString[10], mainDB);
        team.convertFromString(splitString[3], adventureDatabase);
        availableBosses.fromSaveString(splitString[4], adventureDatabase.getAdvCards());
        availableLocations.fromSaveString(splitString[5], adventureDatabase.getSections());
        worlds.fromSaveString(adventureDatabase, mainDB, splitString[6]);
        cardStatTracker.fromSaveString(splitString[7]);
        deckProfiles.fromSaveString(splitString[8], adventureDatabase);
        difficulty = Difficulty.valueOf(splitString[9]);
        miaCardTracker.initialize(numberOfWorlds);
        miaCardTracker.fromSaveString(splitString[11], team.getAllCards());
        timekeeper.fromSaveString(splitString[12]);
        finalWorldUnlocked = Boolean.parseBoolean(splitString[13]);
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

    private void generateAdventure(AdvMainDatabase db, int numTeamMembers, int numCaptains, int numWorlds) {
        adventureDatabase.createNewDatabase(db);
        availableBosses = new AdvCardList(adventureDatabase.getAdvCards());
        availableLocations = new AdvLocationList(adventureDatabase.getSections());
        team = new Team(adventureDatabase, numTeamMembers, numCaptains);
        worlds = new WorldList(numWorlds, adventureDatabase);
        availableBosses.removeAll(worlds.getAllBosses());
        availableLocations.removeAll(worlds.getAllLocations());
        currentWorldNum = 1;
        numberOfWorlds = numWorlds;
        miaCardTracker.initialize(numWorlds);
        cardStatTracker.initialize(getAllCards());
        World w = getCurrentWorld();
        placeInfinityStones();
        w.initialize(getFreeAgents());
    }

    private void createInfinityStones() {
        for (InfinityStoneID id : InfinityStoneID.values()) {
            InfinityStone infinityStone = new InfinityStone(id.getID(), id);
            allInfinityStones.add(infinityStone);
        }
    }

    private void placeInfinityStones() {

        List<Integer> possibleSections = new ArrayList<>();
        for (int i = 0; i < getNumberOfWorlds() * AdventureConstants.SECTIONS_PER_WORLD; i++) {
            possibleSections.add(i);
        }
        Collections.shuffle(possibleSections);
        for (int i = 0; i < allInfinityStones.size(); i++) {
            int choice = possibleSections.get(i);
            int worldNum = (int) Math.floor((double) choice / AdventureConstants.SECTIONS_PER_WORLD) + 1;
            int secNum = choice % AdventureConstants.SECTIONS_PER_WORLD + 1;
            World w = worlds.get(worldNum - 1);
            Section s = w.getSection(secNum);
            s.addReward(allInfinityStones.get(i));
        }
    }

    public World getCurrentWorld() {
        return worlds.get(currentWorldNum - 1);
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
        int worldChosen = random.nextInt(getCurrentWorldNum() + 1, worlds.size()) + 1;
        return worldChosen;
    }


    public void completeCurrentSection() {
        getCurrentWorld().clearSection(getCurrentSectionNum());
        if (getCurrentWorld().numClearedSections() >= difficulty.getSectionsRequiredToClear()) {
            getCurrentWorld().setBossRevealed(true);
        }
        getCurrentWorld().revealNextSection(getCurrentSectionNum());
        logInfo("Section " + getCurrentWorldNum() + "-" + getCurrentSectionNum()
                + " completed");
        getCurrentWorld().incrementCurrentSectionNum();

    }

    public void skipCurrentSection() {
        getCurrentWorld().skipCurrentSection();
        logInfo("Section " + getCurrentWorldNum() + "-" + getCurrentSectionNum()
                + " skipped");
    }

    public void completeCurrentWorld() {
        team.exhaustedCardsRecover();
        //reclaimCards(); Don't need this second call since the cards are reclaimed as part of a different method.
        //Might need the call if completeCurrentWorld is called by another method.
        team.tempCardsExpire();
        logInfo("World " + getCurrentWorldNum()
                + " completed!");
        currentWorldNum++;
        if (currentWorldNum <= getNumberOfWorlds())
            getCurrentWorld().initialize(team.getFreeAgents());
    }

    public ActiveCardList getActiveCards() {
        ActiveCardList active = new ActiveCardList(new ArrayList<>());
        active.addAll(team.getTeamCards().getActiveThings());
        active.addAll(team.getTempCards().getActiveThings());
        return active;
    }

    public ActiveCardList getTeamAndTempCards() {
        ActiveCardList active = new ActiveCardList(new ArrayList<>());
        active.addAll(team.getTeamCards());
        active.addAll(team.getTempCards());
        return active;
    }

    public ActiveCardList getTeamCards() {
        return team.getTeamCards();
    }

    public ActiveCardList getStationedCards() {
        ActiveCardList stationedCards = new ActiveCardList(new ArrayList<>());
        for (Section s : getCurrentWorld().getSections()) {
            stationedCards.addAll(s.getStationedCards());
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

    public void unstationCard(Section s, ActiveCard card) {
        team.unstationCard(s, card);
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
        if (!deck.isEmpty())
            logger.info(newlyExhaustedCards + " exhausted.");
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
        reclaimedCards.addAll(team.retrieveMIACards(miaCardTracker, getCurrentWorldNum() + 1));
        reclaimedCards.addAll(team.retrieveCapturedCards());
        reclaimedCards.addAll(getCurrentWorld().retrieveStationedCards());
        return reclaimedCards;
    }

    public Section enemyEscapes(int sectionNum) {
        Enemy enemy = getCurrentWorld().enemyEscapes(sectionNum);
        int futureWorld = getFutureWorldNum();
        World world = worlds.get(futureWorld - 1);
        Section escapedSection = world.getRandomSection();
        escapedSection.setEnemy(enemy);
        team.sendCapturedCardsAway(miaCardTracker, futureWorld + 1);
        logger.info(enemy + " escaped from world " + getCurrentWorldNum());
        return escapedSection;
    }

    public boolean failStateCheck() {
        ActiveCardList teamCards = getTeamCards();
        ActiveCardList activeCards = getActiveCards();
        if (activeCards.size() < SnapMainConstants.DECK_SIZE) {
            logger.info("Adventure Failed. Not enough active cards to continue.");
            return true;
        }
        if (teamCards.hasNoCaptains()) {
            logger.info("Adventure failed! No captains remaining on team.");
            return true;
        }
        return false;
    }

    public boolean completeStateCheck() {
        if (getCurrentWorldNum() == getNumberOfWorlds() && !team.hasAllInfinityStones()) {
            logger.info("Adventure complete!");
            return true;
        }
        if (getCurrentWorldNum() == getNumberOfWorlds() && team.hasAllInfinityStones()) {
            initializeFinalWorld();
            return false;
        }
        if (getFinalBoss().getCurrentHP() == 0) {
            logger.info("True Adventure complete!");
            return true;
        }
        return false;
    }

    private void initializeFinalWorld() {
        if (getCurrentWorldNum() > getNumberOfWorlds()) {
            World w = new FinalWorld(adventureDatabase, getCurrentWorldNum() + 1,
                    team.getFreeAgents(), availableLocations);
            worlds.set(getCurrentWorldNum(), w);
            logger.info("Final World unlocked.");
            finalWorldUnlocked = true;
        } else if (getCurrentWorldNum() == getNumberOfWorlds()) {
            World finalWorld = worlds.get(worlds.size() - 1);
            finalWorld.initialize(team.getFreeAgents());
        }
    }

    public Enemy getFinalBoss() {
        if(!finalWorldUnlocked)
            return worlds.get(worlds.size() - 2).getBoss();
        return worlds.get(worlds.size() - 1).getBoss();
    }

    public Enemy getTrueFinalBoss()
    {
        if(!finalWorldUnlocked)
            initializeFinalWorld();
        return worlds.get(worlds.size() - 1).getBoss();
    }

    public int getNumberOfWorlds() {
        return numberOfWorlds;
    }

    public Map<Integer, CardStats> getRankedCardStats() {
        return cardStatTracker.getCardStatsSorted();
    }

    public void captureCard(ActiveCard c) {
        team.captureCard(c);
    }

    public void logInfo(String string) {
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

    public void woundCard(ActiveCard c) {
        team.woundCard(c);
        logger.info(c + " wounded!");
    }

    public Section getCurrentBossSection() {
        return getCurrentWorld().getBossSection();
    }


    public void injectCard(ActiveCard activeCard) {
        team.addCardToFreeAgents(activeCard);
    }

    public ActiveCardList getMissingCards(AdvMainDatabase database) {
        return team.getMissingCards(database);
    }

    public Enemy createNewMook() {
        Mook mook = new Mook();
        Enemy enemy = new Enemy(mook);
        enemy.setBaseHP(getCurrentWorld().calculateMookBonus());
        return enemy;
    }
}
