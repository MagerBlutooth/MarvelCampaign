package snapMain.model.database;

import snapMain.model.target.Card;
import snapMain.model.target.Location;
import snapMain.model.target.Token;

public class CampaignDatabase {

    TargetDatabase<Card> cards;
    TargetDatabase<Location> locations;
    TargetDatabase<Token> tokens;
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

    public TargetDatabase<Card> getCards() {
        return cards;
    }

    public TargetDatabase<Location> getLocations() {
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
