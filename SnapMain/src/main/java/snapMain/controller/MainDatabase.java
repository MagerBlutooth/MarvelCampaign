package snapMain.controller;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.database.MasterThingDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;
import snapMain.view.MasterImageCache;
import snapMain.view.IconImage;

import java.util.ArrayList;
import java.util.List;

public class MainDatabase {

    MasterThingDatabase masterThingDatabase;
    MasterImageCache imageCache;

    public MainDatabase(MasterThingDatabase database)
    {
        masterThingDatabase = database;
        imageCache = new MasterImageCache(database);
    }

    public IconImage grabImage(SnapTarget t)
    {
        return imageCache.getImage(t.getID(), t.getTargetType());
    }

    public IconImage grabBlankImage(TargetType tt)
    {
        return imageCache.getImage(CampaignConstants.NO_ICON_ID, tt);
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

    public void saveDatabase(TargetType targetType) {
        masterThingDatabase.saveDatabase(targetType);
    }

    public <T extends SnapTarget> TargetDatabase<T> lookupDatabase(TargetType type) {
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

