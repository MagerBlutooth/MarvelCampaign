package adventure.model.adventure;

import adventure.model.*;
import adventure.model.thing.AdvCardList;
import adventure.model.thing.AdvLocationList;
import snapMain.model.constants.CampaignConstants;
import snapMain.model.thing.Card;

import java.util.ArrayList;
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
    AdvLocationList availableSections;
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
        adventureString.add(availableSections.toSaveString());
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
        availableSections = new AdvLocationList(new ArrayList<>());
        worlds = new WorldList(new ArrayList<>());

        String[] splitString = stringToConvert.get(0).split(CampaignConstants.CSV_SEPARATOR);
        profileName = splitString[0];
        currentWorldNum = Integer.parseInt(splitString[1]);
        team.convertFromString(splitString[3], adventureDatabase.getCards());
        availableBosses.fromSaveString(splitString[4], adventureDatabase.getBosses());
        availableSections.fromSaveString(splitString[5], adventureDatabase.getSections());
        worlds.fromSaveString(adventureDatabase, mainDB, splitString[6]);
        adventureNotes = splitString[7];
        setCurrentSectionNum(Integer.parseInt(splitString[2]));
    }

    private void setCurrentSectionNum(int num) {
        currentSectionNum = num;
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
        availableSections = new AdvLocationList(adventureDatabase.getSections());
        team = new Team(adventureDatabase);
        worlds = new WorldList(adventureDatabase);
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
}
