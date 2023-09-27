package adventure.model;

import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.Location;

import java.util.List;

public class Adventure {

    AdventureDatabase adventureDatabase;
    AdvTeam team;

    List<Card> availableBosses;
    List<Location> locationList;
    List<World> worlds;

    public Adventure(AdventureDatabase database)
    {
        adventureDatabase = database;
        generateAdventure();
    }

    private void generateAdventure() {
        
    }
}
