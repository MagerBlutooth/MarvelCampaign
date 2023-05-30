package model.database;

import model.thing.Card;
import model.thing.Location;
import model.thing.Thing;
import model.thing.Token;

import java.util.List;

public class CampaignDatabase {

    ThingDatabase<Card> cards;
    ThingDatabase<Location> locations;
    ThingDatabase<Token> tokens;
    Location medBay;
    public CampaignDatabase(MasterThingDatabase database)
    {
        cards = database.getEnabledCards();
        locations = database.getEnabledLocations();
        tokens = database.getEnabledTokens();
        medBay = database.getMedbay();
    }

    public ThingDatabase<Card> getCards() {
        return cards;
    }

    public ThingDatabase<Location> getLocations() {
        return locations;
    }

    public Location getMedbay() {
        return medBay;
    }
}
