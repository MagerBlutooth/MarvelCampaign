package adventure.model.adventure;

import adventure.model.*;
import adventure.model.stats.CardStatTracker;
import adventure.model.stats.MatchResult;
import adventure.model.thing.*;
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
    List<InfinityStone> infinityStones;

    //Constructor for loading old profiles
    public Adventure(AdvMainDatabase mainDB, AdventureDatabase database, String proFile, String proName)
    {
        profileName = proName;
        profileFile = proFile;
        adventureDatabase = database;
        newProfileCheck = false;
        infinityStones = new ArrayList<>();
        loadAdventure(profileFile, mainDB);
        cardStatTracker = new CardStatTracker(mainDB, team.getTeamCards());
    }

    //Constructor for creating new profiles
    public Adventure(AdvMainDatabase mainDB, AdventureDatabase database, String proFile)
    {
        profileFile = proFile;
        adventureDatabase = database;
        newProfileCheck = false;
        infinityStones = new ArrayList<>();
        loadAdventure(proFile, mainDB);
        cardStatTracker = new CardStatTracker(mainDB, team.getTeamCards());
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
        return adventureString;
    }

    public void convertFromString(AdvMainDatabase mainDB, List<String> stringToConvert)
    {
        if(stringToConvert.isEmpty())
        {
            generateAdventure();
            return;
        }
        //Initialize base objects
        team = new Team();
        availableBosses = new AdvCardList(new ArrayList<>());
        availableLocations = new AdvLocationList(new ArrayList<>());
        worlds = new WorldList(new ArrayList<>());


        String[] splitString = stringToConvert.get(0).split(SnapMainConstants.CSV_SEPARATOR);
        profileName = splitString[0];
        currentWorldNum = Integer.parseInt(splitString[1]);
        currentSectionNum = Integer.parseInt(splitString[2]);
        team.convertFromString(splitString[3], adventureDatabase.getCards());
        cardStatTracker = new CardStatTracker(mainDB, adventureDatabase.getCardList());
        availableBosses.fromSaveString(splitString[4], adventureDatabase.getBosses());
        availableLocations.fromSaveString(splitString[5], adventureDatabase.getSections());
        worlds.fromSaveString(adventureDatabase, mainDB, splitString[6]);
        cardStatTracker.fromSaveString(splitString[7]);

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

    private void generateAdventure() {
        availableBosses = new AdvCardList(adventureDatabase.getBosses());
        availableLocations = new AdvLocationList(adventureDatabase.getSections());
        team = new Team(adventureDatabase);
        worlds = new WorldList(adventureDatabase);
        availableBosses.removeAll(worlds.getAllBosses());
        availableLocations.removeAll(worlds.getAllLocations());
        currentWorldNum = 1;
        currentSectionNum = 1;
        World w = worlds.get(currentWorldNum);
        w.initializeBoss(getFreeAgents());
        createInfinityStones();
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
            int worldNum = (int) Math.floor((double) choice /AdventureConstants.SECTIONS_PER_WORLD);
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

    public void randomizeSection(Section section) {
        List<AdvLocation> advLocations = new ArrayList<>(availableLocations.getLocations());
        Collections.shuffle(advLocations);
        AdvLocation chosenLoc = advLocations.get(0);
        AdvLocation previousLoc = section.getLocation();
        availableLocations.add(previousLoc);
        availableLocations.remove(chosenLoc);
        section.changeLocation(chosenLoc);
    }

    public void completeCurrentSection()
    {
        getCurrentWorld().clearSection(getCurrentSectionNum());
        if(getCurrentWorld().numClearedSections() >= AdventureConstants.SECTION_CLEAR_GOAL)
        {
            getCurrentWorld().revealBoss();
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

    public CardList reclaimCards() {
        CardList reclaimedCards = new CardList(new ArrayList<>());
        for(Section s: getCurrentWorld().getSections())
        {
            reclaimedCards.addAll(s.getStationedCards().getThings());
            s.getStationedCards().clear();
        }
        reclaimedCards.addAll(team.getCapturedCards().getCards());
        team.getCapturedCards().clear();
        team.getTeamCards().addAll(reclaimedCards.getCards());
        return reclaimedCards;
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
        if(oldLoc != null)
            availableLocations.add(oldLoc);
        availableLocations.remove(newLoc);
    }

    public void updateStats(CardList deck, MatchResult result) {
        cardStatTracker.updateCardStats(deck, result);
        getCurrentWorld().updateWorldStats();
    }

    public CardList getCapturedCards() {
        return team.getCapturedCards();
    }

    public void addCardsToTeam(CardList cards)
    {
        if(cards!=null) {
            team.getFreeAgents().removeAll(cards.getCards());
            team.getTeamCards().addAll(cards.getCards());
        }
    }

    public void addCardToTeam(Card card) {
        if(card!=null) {
            team.getFreeAgents().remove(card);
            team.getTeamCards().add(card);
        }
    }

    public TargetList<Card> getEliminatedCards() {
        return team.getEliminatedCards();
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
}
