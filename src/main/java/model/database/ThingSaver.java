package model.database;

import model.thing.*;

public class ThingSaver {

    public void saveCards(ThingDatabase<Card> cards)
    {
        CardSaver vSaver = new CardSaver(cards);
        vSaver.writeCSV();
    }

    public void saveLocations(ThingDatabase<Location> locs)
    {
        LocationSaver vSaver = new LocationSaver(locs);
        vSaver.writeCSV();
    }

    public void saveTokens(ThingDatabase<Token> tokens) {
        TokenSaver vSaver = new TokenSaver(tokens);
        vSaver.writeCSV();
    }

    public void saveHallOfFame(ThingDatabase<HallOfFameEntry> entries)
    {
        HallOfFameSaver vSaver = new HallOfFameSaver(entries);
        vSaver.writeCSV();
    }

    public void saveDatabase(ThingType type, MasterThingDatabase db) {
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
