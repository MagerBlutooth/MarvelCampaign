package model.database;

import model.thing.Card;
import model.thing.Location;
import model.thing.Token;

public class CampaignDatabase {

    ThingDatabase<Card> cards;
    ThingDatabase<Location> locations;
    ThingDatabase<Token> tokens;
    Location shieldMedbay; //Medbays were divided into two separate objects since they were being treated as one.
    Location hydraMedbay;
    public CampaignDatabase(MasterThingDatabase database)
    {
        cards = database.getEnabledCards();
        locations = database.getEnabledLocations();
        tokens = database.getEnabledTokens();
        shieldMedbay = database.getMedbay(true);
        hydraMedbay = database.getMedbay(false);
    }

    public ThingDatabase<Card> getCards() {
        return cards;
    }

    public ThingDatabase<Location> getLocations() {
        return locations;
    }

    public Location getShieldMedbay() {
        return shieldMedbay;
    }

    public Location getHydraMedbay()
    {
        return hydraMedbay;
    }

    public Location getMedbay(FactionLabel f) {
        switch(f)
        {
            case SHIELD:
                return getShieldMedbay();
            default:
                return getHydraMedbay();
        }
    }
}
