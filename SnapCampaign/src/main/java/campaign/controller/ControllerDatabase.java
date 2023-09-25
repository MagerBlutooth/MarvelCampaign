package campaign.controller;

import campaign.model.database.MasterThingDatabase;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.*;
import campaign.view.MasterImageCache;
import campaign.view.IconImage;

import java.util.ArrayList;
import java.util.List;

public class ControllerDatabase {

    MasterThingDatabase masterThingDatabase;
    MasterImageCache imageCache;

    public ControllerDatabase(MasterThingDatabase database)
    {
        masterThingDatabase = database;
        imageCache = new MasterImageCache(database);
    }

    public IconImage grabImage(Thing t, ThingType tt)
    {
        return imageCache.getImage(t.getID(), tt);
    }

    public void addToken(Token t, IconImage i) {
        masterThingDatabase.addToken(t);
        imageCache.cacheToken(t, i);

    }

    public void addCard(Card c, IconImage i) {
        masterThingDatabase.addCard(c);
        imageCache.cacheCard(c, i);
    }

    public void addLocation(Location l, IconImage i)
    {
        masterThingDatabase.addLocation(l);
        imageCache.cacheLocation(l, i);
    }

    public List<Card> getCards() {
        return masterThingDatabase.getCards();
    }

    public void saveDatabase(ThingType thingType) {
        masterThingDatabase.saveDatabase(thingType);
    }

    public <T extends Thing> ThingDatabase<T> lookupDatabase(ThingType type) {
        return masterThingDatabase.lookupDatabase(type);
    }

    public List<Token> getTokens() {
        return masterThingDatabase.getTokens();
    }

    public List<Location> getLocations() {
        return masterThingDatabase.getLocations();
    }

    public List<Location> getLocationsAndMedbay() {
        return masterThingDatabase.getLocationsAndMedbay();
    }

    public MasterThingDatabase getMasterThingDatabase() {
        return masterThingDatabase;
    }

    public List<Card> getEnabledCards() {
        List<Card> enabledCards = new ArrayList<>();
        for(Card c: getCards())
        {
            if(c.isEnabled())
                enabledCards.add(c);
        }
        return enabledCards;
    }

    public List<Location> getEnabledLocations() {
        List<Location> enabledLocs = new ArrayList<>();
        for(Location l: getLocations())
        {
            if(l.isEnabled())
                enabledLocs.add(l);
        }
        return enabledLocs;
    }
}

