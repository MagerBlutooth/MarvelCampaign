package adventure.model;

import campaign.model.database.DatabaseContext;
import campaign.model.database.MasterThingDatabase;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.*;

public class AdvMasterThingDatabase extends MasterThingDatabase {
    AdvThingFactory vFactory;
    AdvThingSaver vSaver;
    DatabaseContext dBContext;
    ThingDatabase<Card> cards;
    ThingDatabase<Location> locations;
    public AdvMasterThingDatabase(MasterThingDatabase database)
    {
        vFactory = new AdvThingFactory();
        vSaver = new AdvThingSaver();
        dBContext = new DatabaseContext();
        cards = database.getCards();
        locations = database.getLocations();
    }
    public void loadDatabase()
    {
        dBContext.register(ThingType.CARD, vFactory.loadBosses(cards));
        dBContext.register(ThingType.LOCATION, vFactory.loadSections(locations));
    }

    public ThingDatabase<Boss> getBosses() {
        ThingDatabase<Boss> lookup = dBContext.lookup(ThingType.CARD);
        return lookup;
    }

    public ThingDatabase<Section> getSections() {
        ThingDatabase<Section> locs = new ThingDatabase<>();
        locs.addAll(dBContext.lookup(ThingType.LOCATION));
        return locs;
    }

    public <T extends Thing> ThingDatabase<T> lookupDatabase(ThingType t)
    {
        return dBContext.lookup(t);
    }

    public void saveDatabase(ThingType type) {
        vSaver.saveDatabase(type, this);
    }

    public ThingDatabase<Card> getCards() {
        return cards;
    }

    public ThingDatabase<Boss> getEnabledBosses() {
        ThingDatabase<Boss> enabled = new ThingDatabase<>();
        for(Boss b: getBosses())
        {
            if(b.isEnabled())
                enabled.add(b);
        }
        return enabled;
    }

    public ThingDatabase<Section> getEnabledSections() {
        ThingDatabase<Section> enabled = new ThingDatabase<>();
        for(Section s: getSections())
        {
            if(s.isEnabled())
                enabled.add(s);
        }
        return enabled;
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

    public void modifyBoss(Boss b) {
        ThingDatabase<Boss> lookup = dBContext.lookup(ThingType.CARD);
        lookup.addNewEntry(b);
        vSaver.saveBosses(getBosses());
    }

    public void modifySection(Section s)
    {
        ThingDatabase<Section> lookup = dBContext.lookup(ThingType.LOCATION);
        lookup.addNewEntry(s);
        vSaver.saveSections(getSections());
    }

    public Boss getBoss(Card card)
    {
        return getBosses().lookup(card.getID());
    }

    public Section getSection(Location loc)
    {
        return getSections().lookup(loc.getID());
    }

    public void toggleBoss(Card card) {
        Boss boss = getBoss(card);
        boss.setEnabled(!boss.isEnabled());
    }

    public void toggleSection(Location loc) {
        Section s = getSection(loc);
        s.setEnabled(!loc.isEnabled());
    }
}