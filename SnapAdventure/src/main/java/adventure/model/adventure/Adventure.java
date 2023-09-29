package adventure.model.adventure;

import adventure.model.*;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;

import java.util.ArrayList;
import java.util.List;

public class Adventure {

    String profileName;
    AdventureDatabase adventureDatabase;
    AdventureSaver saver;
    AdventureLoader loader;
    Team team;
    BossList availableBosses;
    SectionList availableSections;
    WorldList worlds;
    String adventureNotes;
    int currentWorld;

    public Adventure(AdventureDatabase database)
    {
        profileName = "Chara";
        adventureDatabase = database;
        adventureNotes = "";
        generateAdventure();
    }

    public Adventure(AdventureDatabase database, String profileFile)
    {
        profileName = "Chara";
        adventureDatabase = database;
        loadAdventure(profileFile);
    }

    public List<String> convertToString()
    {
        List<String> adventureString = new ArrayList<>();
        adventureString.add(profileName);
        adventureString.add(currentWorld+"");
        World world = worlds.get(currentWorld);
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
        profileName = stringToConvert.get(0);
        currentWorld = Integer.parseInt(stringToConvert.get(1));

        team.convertFromString(stringToConvert.get(3), adventureDatabase.getCards());
        availableBosses.fromSaveString(stringToConvert.get(4), adventureDatabase.getBosses());
        availableSections.fromSaveString(stringToConvert.get(5), adventureDatabase.getSections());
        worlds.fromSaveString(adventureDatabase, stringToConvert.get(6));
        adventureNotes = stringToConvert.get(7);
        World world = worlds.get(currentWorld);
        world.setCurrentSectionNum(Integer.parseInt(stringToConvert.get(2)));
    }

    private void saveAdventure(String profile)
    {
        saver = new AdventureSaver(profile, this);
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
        currentWorld = 1;
    }

    public World getCurrentWorld() {
        return worlds.get(currentWorld);
    }

    public int getCurrentSectionNum() {
        return worlds.get(currentWorld).getCurrentSectionNum();
    }

    public Team getTeam() {
        return team;
    }
}
