package adventure.model.adventure;

import adventure.model.*;
import campaign.model.constants.CampaignConstants;

import java.util.ArrayList;
import java.util.List;

public class Adventure {

    String profileName;
    String profileFile;
    AdventureDatabase adventureDatabase;
    AdventureSaver saver;
    AdventureLoader loader;
    Team team;
    BossList availableBosses;
    SectionList availableSections;
    WorldList worlds;
    String adventureNotes;
    int currentWorldNum;
    public Adventure(AdventureDatabase database, String proFile, String proName)
    {
        profileName = proName;
        profileFile = proFile;
        adventureDatabase = database;
        loadAdventure(profileFile);
    }

    public Adventure(AdventureDatabase database, String proFile)
    {
        profileFile = proFile;
        adventureDatabase = database;
        loadAdventure(proFile);
    }

    public List<String> convertToString()
    {
        List<String> adventureString = new ArrayList<>();
        adventureString.add(profileName);
        adventureString.add(currentWorldNum +"");
        World world = worlds.get(currentWorldNum);
        adventureString.add(world.getCurrentSectionNum()+"");
        adventureString.add(team.convertToString());
        adventureString.add(availableBosses.toSaveString());
        adventureString.add(availableSections.toSaveString());
        adventureString.add(worlds.toSaveString());
        adventureString.add("\"" +adventureNotes + "\"");
        return adventureString;
    }

    public void convertFromString(List<String> stringToConvert)
    {
        if(stringToConvert.isEmpty())
        {
            generateAdventure();
            return;
        }
        //Initialize base objects
        team = new Team(adventureDatabase);
        availableBosses = new BossList(new ArrayList<>());
        availableSections = new SectionList(new ArrayList<>());
        worlds = new WorldList(new ArrayList<>());

        String[] splitString = stringToConvert.get(0).split(CampaignConstants.CSV_SEPARATOR);
        profileName = splitString[0];
        currentWorldNum = Integer.parseInt(splitString[1]);
        team.convertFromString(splitString[3], adventureDatabase.getCards());
        availableBosses.fromSaveString(splitString[4], adventureDatabase.getBosses());
        availableSections.fromSaveString(splitString[5], adventureDatabase.getSections());
        worlds.fromSaveString(adventureDatabase, splitString[6]);
        adventureNotes = splitString[7];
        World world = worlds.get(currentWorldNum);
        world.setCurrentSectionNum(Integer.parseInt(splitString[2]));
    }

    public void saveAdventure()
    {
        saver = new AdventureSaver(profileFile, this);
        saver.writeFile();
    }

    private void loadAdventure(String profile) {
        loader = new AdventureLoader(profile);
        List<String> adventureFile = loader.readFile();
        List<String> adventureString = new ArrayList<>(adventureFile);
        convertFromString(adventureString);
    }

    public void editNotes(String n)
    {
        adventureNotes = n;
    }

    private void generateAdventure() {
        availableBosses = new BossList(adventureDatabase.getBosses());
        availableSections = new SectionList(adventureDatabase.getSections());
        team = new Team(adventureDatabase);
        worlds = new WorldList(adventureDatabase);
        currentWorldNum = 1;
    }

    public World getCurrentWorld() {
        return worlds.get(currentWorldNum);
    }

    public int getCurrentSectionNum() {
        return worlds.get(currentWorldNum).getCurrentSectionNum();
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
}
