package adventure.model;

import adventure.model.thing.*;
import snapMain.model.database.DatabaseContext;
import snapMain.model.database.MasterThingDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;

public class AdvMasterThingDatabase extends MasterThingDatabase {
    AdvThingFactory vFactory;
    AdvThingSaver vSaver;
    DatabaseContext dBContext;
    TargetDatabase<Card> cards;
    TargetDatabase<Location> locations;
    TargetDatabase<Token> tokens;
    public AdvMasterThingDatabase(MasterThingDatabase database)
    {
        vFactory = new AdvThingFactory();
        vSaver = new AdvThingSaver();
        dBContext = new DatabaseContext();
        cards = database.getCards();
        locations = database.getLocations();
        tokens = database.getTokens();
    }
    @Override
    public void loadDatabase()
    {
        dBContext.register(TargetType.ADV_CARD, vFactory.loadBosses(cards));
        dBContext.register(TargetType.CARD, cards);
        dBContext.register(TargetType.LOCATION, vFactory.loadSections(locations));
        dBContext.register(TargetType.TOKEN, tokens);
    }

    public TargetDatabase<AdvCard> getBosses() {
        TargetDatabase<AdvCard> lookup = dBContext.lookup(TargetType.ADV_CARD);
        return lookup;
    }

    public TargetDatabase<AdvLocation> getSections() {
        TargetDatabase<AdvLocation> locs = new TargetDatabase<>();
        locs.addAll(dBContext.lookup(TargetType.LOCATION));
        return locs;
    }

    public <T extends SnapTarget> TargetDatabase<T> lookupDatabase(TargetType t)
    {
        return dBContext.lookup(t);
    }

    public void saveDatabase(TargetType type) {
        vSaver.saveDatabase(type, this);
    }

    public TargetDatabase<Card> getCards() {
        return dBContext.lookup(TargetType.CARD);
    }

    public TargetDatabase<Token> getTokens()
    {
        return dBContext.lookup(TargetType.TOKEN);
    }

    public void modifyBoss(AdvCard b) {
        TargetDatabase<AdvCard> lookup = dBContext.lookup(TargetType.ADV_CARD);
        lookup.addNewEntry(b);
        vSaver.saveBosses(getBosses());
    }

    public void modifySection(AdvLocation s)
    {
        TargetDatabase<AdvLocation> lookup = dBContext.lookup(TargetType.LOCATION);
        lookup.addNewEntry(s);
        vSaver.saveSections(getSections());
    }

    public TargetDatabase<AdvCard> getEnabledAdvCards() {
        TargetDatabase<AdvCard> enabled = new TargetDatabase<>();
        for(AdvCard b: getBosses())
        {
            if(b.isEnabled())
                enabled.add(b);
        }
        return enabled;
    }

    public TargetDatabase<AdvLocation> getEnabledAdvLocations() {
        TargetDatabase<AdvLocation> enabled = new TargetDatabase<>();
        for(AdvLocation s: getSections())
        {
            if(s.isEnabled())
                enabled.add(s);
        }
        return enabled;
    }

    @Override
    public TargetDatabase<Card> getEnabledCards()
    {
        TargetDatabase<Card> enabled = new TargetDatabase<>();
        for(Card c: getCards())
        {
            AdvCard b = getBoss(c);
            if(b.isEnabled())
                enabled.add(c);
        }
        return enabled;
    }

    public AdvCard getBoss(Card card)
    {
        return getBosses().lookup(card.getID());
    }

    public AdvLocation getSection(Location loc)
    {
        return getSections().lookup(loc.getID());
    }

    public void toggleBoss(Card card) {
        AdvCard advCard = getBoss(card);
        advCard.setEnabled(!advCard.isEnabled());
    }

    public void toggleSection(Location loc) {
        AdvLocation s = getSection(loc);
        s.setEnabled(!loc.isEnabled());
    }
}