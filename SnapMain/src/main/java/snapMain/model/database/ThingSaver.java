package snapMain.model.database;

import snapMain.model.target.*;

public class ThingSaver {

    public void saveCards(TargetDatabase<Card> cards)
    {
        CardSaver vSaver = new CardSaver(cards);
        vSaver.writeCSV();
    }

    public void saveLocations(TargetDatabase<Location> locs)
    {
        LocationSaver vSaver = new LocationSaver(locs);
        vSaver.writeCSV();
    }

    public void saveTokens(TargetDatabase<Token> tokens) {
        TokenSaver vSaver = new TokenSaver(tokens);
        vSaver.writeCSV();
    }

    public void saveDatabase(TargetType type, MasterThingDatabase db) {
        switch(type)
        {
            case CARD:
                saveCards(db.getCards());
            case LOCATION:
                saveLocations(db.getLocationsAndMedbay());
            case TOKEN:
                saveTokens(db.getTokens());
        }
    }
}
