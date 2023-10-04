package snapMain.model.database;

import snapMain.model.factory.ThingFactory;
import snapMain.model.target.*;

import static snapMain.model.constants.CampaignConstants.HYDRA_MEDBAY_ID;
import static snapMain.model.constants.CampaignConstants.SHIELD_MEDBAY_ID;

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
        dBContext.register(TargetType.CARD, vFactory.loadCards());
        dBContext.register(TargetType.LOCATION, vFactory.loadLocations());
        dBContext.register(TargetType.TOKEN, vFactory.loadTokens());
    }

    public TargetDatabase<Card> getCards() {
        return (TargetDatabase<Card>) dBContext.lookup(TargetType.CARD);
    }

    public TargetDatabase<Token> getTokens()
    {
        return (TargetDatabase<Token>) dBContext.lookup(TargetType.TOKEN);
    }

    public TargetDatabase<Location> getLocations() {
        TargetDatabase<Location> locs = new TargetDatabase<>();
        locs.addAll(dBContext.lookup(TargetType.LOCATION));
        locs.remove(getMedbay(true));
        locs.remove(getMedbay(false));
        return locs;
    }

    public TargetDatabase<Location> getLocationsAndMedbay() {
        return dBContext.lookup(TargetType.LOCATION);
    }

    public <T extends SnapTarget> TargetDatabase<T> lookupDatabase(TargetType t)
    {
        return dBContext.lookup(t);
    }

    public void addCard(Card c) {
        TargetDatabase<Card> lookup = dBContext.lookup(TargetType.CARD);
        lookup.addNewEntry(c);
        vSaver.saveCards(getCards());
    }

    public void addLocation(Location l) {
        TargetDatabase<Location> lookup = dBContext.lookup((TargetType.LOCATION));
        lookup.addNewEntry(l);
        vSaver.saveLocations(getLocationsAndMedbay());
    }

    public void addToken(Token t) {
        TargetDatabase<Token> lookup = dBContext.lookup((TargetType.TOKEN));
        lookup.addNewEntry(t);
        vSaver.saveTokens(getTokens());
    }

    public void saveDatabase(TargetType type) {
        vSaver.saveDatabase(type, this);
    }
    public Location getMedbay(boolean isShield) {
        TargetDatabase<Location> locs = dBContext.lookup(TargetType.LOCATION);
        if(isShield)
            return locs.lookup(SHIELD_MEDBAY_ID);
        return locs.lookup(HYDRA_MEDBAY_ID);
    }

    public TargetDatabase<Card> getEnabledCards()
    {
        TargetDatabase<Card> enabled = new TargetDatabase<>();
        for(Card c: getCards())
        {
            if(c.isEnabled())
                enabled.add(c);
        }
        return enabled;
    }

    public TargetDatabase<Location> getEnabledLocations()
    {
        TargetDatabase<Location> enabled = new TargetDatabase<>();
        for(Location l: getLocations())
        {
            if(l.isEnabled())
                enabled.add(l);
        }
        return enabled;
    }

    public TargetDatabase<Token> getEnabledTokens() {
        TargetDatabase<Token> enabled = new TargetDatabase<>();
        for(Token t: getTokens())
        {
            if(t.isEnabled())
                enabled.add(t);
        }
        return enabled;
    }
}