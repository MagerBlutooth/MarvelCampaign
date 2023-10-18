package adventure.model.adventure;

import adventure.model.*;
import adventure.model.stats.CardStatTracker;
import adventure.model.stats.MatchResult;
import adventure.model.target.*;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Adventure {

    String profileName;
    String profileFile;
    AdventureDatabase adventureDatabase;
    AdventureSaver saver;
    AdventureLoader loader;
    Team team;
    AdvCardList availableBosses;
    AdvLocationList availableLocations;
    WorldList worlds;
    int currentWorldNum;
    int currentSectionNum;
    boolean newProfileCheck;
    CardStatTracker cardStatTracker;
    DeckProfileList deckProfiles;
    List<InfinityStone> infinityStones;
    Difficulty difficulty;

    public Adventure(AdvMainDatabase mainDB, String proFile)
    {
        //Initialize base objects
        team = new Team();
        availableBosses = new AdvCardList(new ArrayList<>());
        availableLocations = new AdvLocationList(new ArrayList<>());
        worlds = new WorldList(new ArrayList<>());
        profileFile = proFile;
        adventureDatabase = new AdventureDatabase();
        newProfileCheck = false;
        infinityStones = new ArrayList<>();
        cardStatTracker = new CardStatTracker();
        deckProfiles = new DeckProfileList(SnapMainConstants.DECK_PROFILE_DEFAULT);
        createInfinityStones();
        loadAdventure(proFile, mainDB);
    }

    public void initialize(AdvMainDatabase db, int numTeamMembers, int numTeamCaptains, Difficulty d) {
        difficulty = d;
        generateAdventure(db, numTeamMembers, numTeamCaptains);
    }

    public List<String> convertToString()
    {
        List<String> adventureString = new ArrayList<>();
        adventureString.add(profileName);
        adventureString.add(currentWorldNum +"");
        adventureString.add(currentSectionNum+"");
        adventureString.add(team.convertToString());
        adventureString.add(availableBosses.toSaveString());
        adventureString.add(availableLocations.toSaveString());
        adventureString.add(worlds.toSaveString());
        adventureString.add(cardStatTracker.toSaveString());
        adventureString.add(deckProfiles.toSaveString());
        adventureString.add(difficulty.toString());
        adventureString.add(adventureDatabase.toSaveString());
        return adventureString;
    }

    public void convertFromString(AdvMainDatabase mainDB, List<String> stringToConvert)
    {
        if(stringToConvert.isEmpty())
        {
            return;
        }

        String[] splitString = stringToConvert.get(0).split(SnapMainConstants.CSV_SEPARATOR);

        profileName = splitString[0];
        currentWorldNum = Integer.parseInt(splitString[1]);
        currentSectionNum = Integer.parseInt(splitString[2]);
        team.convertFromString(splitString[3], mainDB.lookupDatabase(TargetType.CARD));
        cardStatTracker = new CardStatTracker();
        adventureDatabase.fromSaveString(splitString[10], mainDB);
        availableBosses.fromSaveString(splitString[4], adventureDatabase.getBosses());
        availableLocations.fromSaveString(splitString[5], adventureDatabase.getSections());
        worlds.fromSaveString(adventureDatabase, mainDB, splitString[6]);
        cardStatTracker.fromSaveString(splitString[7]);
        deckProfiles.fromSaveString(splitString[8], mainDB.lookupDatabase(TargetType.CARD));
        difficulty = Difficulty.valueOf(splitString[9]);
    }

    public void saveAdventure()
    {
        saver = new AdventureSaver(profileFile, this);
        saver.writeFile();
    }

    private void loadAdventure(String profile, AdvMainDatabase db) {
        loader = new AdventureLoader(profile);
        List<String> adventureFile = loader.readFile();
        List<String> adventureString = new ArrayList<>(adventureFile);
        convertFromString(db, adventureString);
    }

    private void generateAdventure(AdvMainDatabase db, int numTeamMembers, int numCaptains) {
        adventureDatabase.createNewDatabase(db);
        availableBosses = new AdvCardList(adventureDatabase.getBosses());
        availableLocations = new AdvLocationList(adventureDatabase.getSections());
        team = new Team(adventureDatabase, numTeamMembers, numCaptains);
        worlds = new WorldList(adventureDatabase);
        availableBosses.removeAll(worlds.getAllBosses());
        availableLocations.removeAll(worlds.getAllLocations());
        currentWorldNum = 1;
        currentSectionNum = 1;
        cardStatTracker.initialize(adventureDatabase.getCards());
        World w = worlds.get(currentWorldNum);
        w.initializeBoss(getFreeAgents());
        placeInfinityStones();
    }

    private void createInfinityStones()
    {
        for(InfinityStoneID id: InfinityStoneID.values())
        {
            InfinityStone infinityStone = new InfinityStone(id.getID(), id);
            infinityStones.add(infinityStone);
        }
    }

    private void placeInfinityStones() {

        List<Integer> possibleSections = new ArrayList<>();
        for(int i = 0; i < AdventureConstants.NUMBER_OF_WORLDS*AdventureConstants.SECTIONS_PER_WORLD; i++)
        {
            possibleSections.add(i);
        }
        Collections.shuffle(possibleSections);
        for(int i = 0; i < infinityStones.size(); i++)
        {
            int choice = possibleSections.get(i);
            int worldNum = (int) Math.floor((double) choice/AdventureConstants.SECTIONS_PER_WORLD);
            int secNum = choice % AdventureConstants.SECTIONS_PER_WORLD + 1;
            World w = worlds.get(worldNum);
            Section s = w.getSection(secNum);
            s.addPickup(infinityStones.get(i));
        }
    }

    public World getCurrentWorld() {
        return worlds.get(currentWorldNum);
    }

    public int getCurrentSectionNum() {
        return currentSectionNum;
    }

    public Team getTeam() {
        return team;
    }

    public String getProfileName() {
        return profileName;
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

    public void sendAway(Card card) {
        World world = getFutureWorld();
        if(world != null)
        {
            world.getRandomSection().stationCard(card);
        }
    }

    private World getFutureWorld() {
        Random random = new Random();
        int worldChosen = random.nextInt(worlds.size()-1) + 1;
        return worlds.get(worldChosen);
    }


    public void completeCurrentSection()
    {
        getCurrentWorld().clearSection(getCurrentSectionNum());
        if(getCurrentWorld().numClearedSections() >= difficulty.getSectionsRequiredToClear())
        {
            getCurrentWorld().setBossRevealed(true);
        }
        getCurrentWorld().revealNextSection(currentSectionNum);
        incrementCurrentSectionNum();
    }

    public void incrementCurrentSectionNum() {
        if(currentSectionNum == AdventureConstants.SECTIONS_PER_WORLD)
            currentSectionNum = 1;
        else
            currentSectionNum++;
    }

    public void completeCurrentWorld() {
        if(currentWorldNum < AdventureConstants.NUMBER_OF_WORLDS) {
            currentWorldNum++;
            currentSectionNum = 1;
            team.retrieveCapturedCards();
            team.loseTempCards();
            getCurrentWorld().initializeBoss(team.getFreeAgents());
        }
        else if(infinityStones.size()==6)
        {
            currentWorldNum++;
            worlds.add(new World(adventureDatabase));
        }
    }

    public CardList getActiveCards()
    {
        CardList active = new CardList(new ArrayList<>());
        active.addAll(team.getTeamCards().getThings());
        active.addAll(team.getTempCards().getThings());
        return active;
    }

    public TargetList<Card> getTeamCards() {
        return team.getTeamCards();
    }

    public CardList getStationedCards()
    {
        CardList stationedCards = new CardList(new ArrayList<>());
        for(Section s: getCurrentWorld().getSections()) {
            stationedCards.addAll(s.getStationedCards().getThings());
        }
        return stationedCards;
    }

    //Normal method for drafting cards
    public TargetList<Card> draftCards() {
        CardList cards = new CardList(new ArrayList<>());
        CardList freeAgents = new CardList(team.getFreeAgents());
        Collections.shuffle(freeAgents.getCards());
        cards.addAll(freeAgents.subList(0, AdventureConstants.NUM_DRAFT_CARDS));
        return cards;
    }

    //Draft specific cards from a predefined subset
    public TargetList<Card> draftCards(TargetList<Card> subset) {
        CardList cards = new CardList(new ArrayList<>());
        CardList freeAgents = new CardList(subset.getThings());
        Collections.shuffle(freeAgents.getCards());
        cards.addAll(freeAgents.subList(0, AdventureConstants.NUM_DRAFT_CARDS));
        return cards;
    }

    public void addFreeAgentToTeam(Card card) {
        team.getTeamCards().add(card);
        team.getFreeAgents().remove(card);
    }

    public void healCard(Card card) {
        team.healCard(card);
    }

    public TargetList<Card> getWoundedCards() {
        return team.getWoundedCards();
    }

    public void stationCard(Section s, Card card) {
        team.stationCard(s, card);
    }

    public void addFreeAgentToTemp(Card card) {
        team.getTempCards().add(card);
        team.getFreeAgents().remove(card);
    }

    public CardList getFreeAgents() {
        return team.getFreeAgents();
    }

    public void collectPickups(Section section) {
        TargetList<Playable> pickups = section.getPickups();
        for(Playable p: pickups)
        {
            if(p instanceof Card) {
                team.addCardToTeam((Card)p);
                team.getMIACards().remove((Card)p);
            }
            else if(p instanceof InfinityStone)
            {
                team.gainInfinityStone((InfinityStone)p);
            }
        }
            pickups.clear();
    }

    public AdvLocationList getAvailableLocations() {
        return availableLocations;
    }

    public void updateSection(AdvLocation newLoc, int sectionNum) {
        World w = getCurrentWorld();
        AdvLocation oldLoc = w.getSection(sectionNum).getLocation();
        w.updateSection(newLoc, sectionNum);
        if(oldLoc.isActualThing())
            availableLocations.add(oldLoc);
        if(newLoc.isActualThing())
            availableLocations.remove(newLoc);
    }
    public void destroySection(int sectionNum) {
        World w = getCurrentWorld();
        AdvLocation oldLoc = w.getSection(sectionNum).getLocation();
        w.updateSection(new AdvLocation(new Location()), sectionNum);
    }

    public void updateStats(CardList deck, MatchResult result) {
        cardStatTracker.updateCardStats(deck, result);
        getCurrentWorld().updateWorldStats(result);
    }

    public void addCardToTeam(Card card) {
        if(card!=null) {
            team.getFreeAgents().remove(card);
            team.getTeamCards().add(card);
        }
    }

    public Card getBossCard() {
        World w = getCurrentWorld();
        AdvCard boss =  (AdvCard) w.getBoss().getSubject();
        return boss.getCard();
    }
    public int getTotalMatchCount()
    {
        int matchCount = 0;
        for(World w: worlds)
        {
            matchCount += w.getMatchCount();
        }
        return matchCount;
    }

    public int getWorldMatchCount() {
        return getCurrentWorld().getMatchCount();
    }

    public DeckProfileList getDeckProfiles() {
        return deckProfiles;
    }

    public void updateDeckProfiles(DeckProfileList list, int latest)
    {
        deckProfiles = list;
        deckProfiles.setLatestProfileNum(latest);
    }

    public Enemy replaceEnemy(Playable p, int sectionNum, boolean clone) {
        Section s = getCurrentWorld().getSection(sectionNum);
        Enemy enemy;
        if(s instanceof BossSection)
            enemy = new Enemy(p, getCurrentWorld().calculateBossBonus());
        else
            enemy = new Enemy(p, getCurrentWorld().calculateMookBonus());
        SnapTarget oldEnemy = s.replaceEnemy(enemy);

        if(oldEnemy instanceof AdvCard)
        {
            Card oldEnemyCard = ((AdvCard) oldEnemy).getCard();
            if(oldEnemyCard.getID() != SnapMainConstants.NO_ICON_ID && !clone)
                team.addCardToFreeAgents(oldEnemyCard);
        }
        if(p instanceof AdvCard && !clone) {
            Card enemyCard = ((AdvCard)p).getCard();
            team.removeFromFreeAgents(enemyCard);
        }
        return enemy;

    }

    public int getCurrentWorldNum() {
        return getCurrentWorld().getWorldNum();
    }

    public AdventureDatabase getAdventureDatabase() {
        return adventureDatabase;
    }

    public boolean reclaimCard(Card card) {
        boolean reclaimed = false;
        for(Section s: getCurrentWorld().getSections())
        {
            if(s.getStationedCards().contains(card))
            {
                reclaimed = s.removeStationedCard(card, team);
            }
        }
        if(!reclaimed)
            reclaimed = team.returnCard(card);
        return reclaimed;
    }


    public void addFreeAgentsToTeam(CardList cards) {
        for(Card c: cards)
        {
            addFreeAgentToTeam(c);
        }
    }

    public void addFreeAgentsToTemp(CardList cards) {
        for(Card c: cards)
        {
            addFreeAgentToTemp(c);
        }
    }

}
