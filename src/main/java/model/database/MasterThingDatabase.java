package model.database;

import model.factory.ThingFactory;
import model.thing.*;

import static model.constants.CampaignConstants.MEDBAY_ID;

public class MasterThingDatabase {

    ThingFactory vFactory;
    ThingSaver vSaver;
    DatabaseContext dBContext;
    public MasterThingDatabase()
    {
        vFactory = new ThingFactory();
        vSaver = new ThingSaver();
        dBContext = new DatabaseContext();
    }
    public void loadDatabase()
    {
        dBContext.register(ThingType.CARD, vFactory.loadCards());
        dBContext.register(ThingType.LOCATION, vFactory.loadLocations());
        dBContext.register(ThingType.TOKEN, vFactory.loadTokens());
    }

    public ThingDatabase<Card> getCards() {
        ThingDatabase<Card> lookup = dBContext.lookup(ThingType.CARD);
        return lookup;
    }

    public ThingDatabase<Token> getTokens()
    {
        return dBContext.lookup(ThingType.TOKEN);
    }

    public ThingDatabase<Location> getLocations() {
        ThingDatabase<Location> locs = new ThingDatabase<>();
        locs.addAll(dBContext.lookup(ThingType.LOCATION));
        locs.remove(getMedbay());
        return locs;
    }

    public ThingDatabase<Location> getLocationsAndMedbay() {
        return dBContext.lookup(ThingType.LOCATION);
    }

    public <T extends Thing> ThingDatabase<T> lookupDatabase(ThingType t)
    {
        return dBContext.lookup(t);
    }

    public void addCard(Card c) {
        ThingDatabase<Card> lookup = dBContext.lookup(ThingType.CARD);
        lookup.addNewEntry(c);
        vSaver.saveCards(getCards());
    }

    public void addLocation(Location l) {
        ThingDatabase<Location> lookup = dBContext.lookup((ThingType.LOCATION));
        lookup.addNewEntry(l);
        vSaver.saveLocations(getLocationsAndMedbay());
    }

    public void addToken(Token t) {
        ThingDatabase<Token> lookup = dBContext.lookup((ThingType.TOKEN));
        lookup.addNewEntry(t);
        vSaver.saveTokens(getTokens());
    }

    public void saveDatabase(ThingType type) {
        vSaver.saveDatabase(type, this);
    }
    public Location getMedbay() {
        ThingDatabase<Location> locs = dBContext.lookup(ThingType.LOCATION);
        return locs.lookup(MEDBAY_ID);
    }

    public ThingDatabase<Card> getEnabledCards()
    {
        ThingDatabase<Card> enabled = new ThingDatabase<>();
        for(Card c: getCards())
        {
            if(c.isEnabled())
                enabled.add(c);
        }
        return enabled;
    }

    public ThingDatabase<Location> getEnabledLocations()
    {
        ThingDatabase<Location> enabled = new ThingDatabase<>();
        for(Location l: getLocations())
        {
            if(l.isEnabled())
                enabled.add(l);
        }
        return enabled;
    }

    public ThingDatabase<Token> getEnabledTokens() {
        ThingDatabase<Token> enabled = new ThingDatabase<>();
        for(Token t: getTokens())
        {
            if(t.isEnabled())
                enabled.add(t);
        }
        return enabled;
    }
}