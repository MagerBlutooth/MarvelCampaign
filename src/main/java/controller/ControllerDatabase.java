package controller;

import model.database.MasterThingDatabase;
import model.database.ThingDatabase;
import model.thing.*;
import view.CampaignImageCache;
import view.IconImage;

import java.util.ArrayList;
import java.util.List;

public class ControllerDatabase {

    MasterThingDatabase masterThingDatabase;
    CampaignImageCache imageCache;

    public ControllerDatabase(MasterThingDatabase database)
    {
        masterThingDatabase = database;
        imageCache = new CampaignImageCache(database);
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

