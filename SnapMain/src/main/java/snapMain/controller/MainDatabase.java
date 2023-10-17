package snapMain.controller;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.MasterThingDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;
import snapMain.view.MasterImageCache;
import snapMain.view.IconImage;
import snapMain.view.grabber.IconConstant;

import java.util.ArrayList;
import java.util.List;

public class MainDatabase {

    MasterThingDatabase masterThingDatabase;
    MasterImageCache masterImageCache;

    public MainDatabase(MasterThingDatabase database)
    {
        masterThingDatabase = database;
        masterImageCache = new MasterImageCache(database);
    }

    public IconImage grabImage(SnapTarget t)
    {
        return masterImageCache.getImage(t.getID(), t.getTargetType());
    }
    
    public IconImage grabIcon(IconConstant i)
    {
        return masterImageCache.getIcon(i);
    }

    public IconImage grabBlankImage(TargetType tt)
    {
        return masterImageCache.getImage(SnapMainConstants.NO_ICON_ID, tt);
    }

    public void addToken(Token t, IconImage i) {
        masterThingDatabase.addToken(t);
        masterImageCache.cacheToken(t, i);
    }

    public void addCard(Card c, IconImage i) {
        masterThingDatabase.addCard(c);
        masterImageCache.cacheCard(c, i);
    }

    public void addLocation(Location l, IconImage i)
    {
        masterThingDatabase.addLocation(l);
        masterImageCache.cacheLocation(l, i);
    }

    public List<Card> getCards() {
        return masterThingDatabase.getCards();
    }

    public void saveDatabase(TargetType targetType) {
        masterThingDatabase.saveDatabase(targetType);
    }

    public <T extends SnapTarget> TargetDatabase<T> lookupDatabase(TargetType type) {
        return masterThingDatabase.lookupDatabase(type);
    }

    public TargetDatabase<Token> getTokens() {
        return masterThingDatabase.getTokens();
    }

    public List<Location> getLocations() {
        return masterThingDatabase.getLocations();
    }

    public List<Location> getLocationsAndMedbay() {
        return masterThingDatabase.getLocationsAndMedbay();
    }

    public MasterThingDatabase getAdvMasterThingDatabase() {
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

