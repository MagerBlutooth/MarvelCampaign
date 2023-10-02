package adventure.model.adventure;

import adventure.model.*;
import adventure.model.thing.AdvCardList;
import adventure.model.thing.AdvLocation;
import adventure.model.thing.AdvLocationList;
import adventure.model.thing.Section;
import snapMain.model.constants.CampaignConstants;
import snapMain.model.thing.Card;

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
    String adventureNotes;
    int currentWorldNum;
    int currentSectionNum;
    boolean newProfileCheck;
    //Constructor for loading old profiles
    public Adventure(AdvMainDatabase mainDB, AdventureDatabase database, String proFile, String proName)
    {
        profileName = proName;
        profileFile = proFile;
        adventureDatabase = database;
        adventureNotes = "";
        newProfileCheck = false;
        loadAdventure(profileFile, mainDB);
    }

    //Constructor for creating new profiles
    public Adventure(AdvMainDatabase mainDB, AdventureDatabase database, String proFile)
    {
        profileFile = proFile;
        adventureDatabase = database;
        adventureNotes = "";
        newProfileCheck = false;
        loadAdventure(proFile, mainDB);
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
        adventureString.add("\"" +adventureNotes + "\"");
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

        String[] splitString = stringToConvert.get(0).split(CampaignConstants.CSV_SEPARATOR);
        profileName = splitString[0];
        currentWorldNum = Integer.parseInt(splitString[1]);
        currentSectionNum = Integer.parseInt(splitString[2]);
        team.convertFromString(splitString[3], adventureDatabase.getCards());
        availableBosses.fromSaveString(splitString[4], adventureDatabase.getBosses());
        availableLocations.fromSaveString(splitString[5], adventureDatabase.getSections());
        worlds.fromSaveString(adventureDatabase, mainDB, splitString[6]);
        adventureNotes = splitString[7];

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

    public void editNotes(String n)
    {
        adventureNotes = n;
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

    public int getCurrentWorldNum() {
        return currentWorldNum;
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
            world.getRandomSection().addStation(card);
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
        if(getCurrentWorld().numClearedSections() == AdventureConstants.SECTION_CLEAR_GOAL)
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
}
