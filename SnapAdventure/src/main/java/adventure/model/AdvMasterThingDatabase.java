package adventure.model;

import adventure.model.target.*;
import snapMain.model.database.DatabaseContext;
import snapMain.model.database.MasterThingDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;

import java.util.List;

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
        dBContext.register(TargetType.ADV_TOKEN, vFactory.loadAdvTokens(tokens));
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

    public TargetDatabase<AdvToken> getAdvTokens() {
        TargetDatabase<AdvToken> tokens = new TargetDatabase<>();
        tokens.addAll(dBContext.lookup(TargetType.ADV_TOKEN));
        return tokens;
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

    public void modifyAdvToken(AdvToken a) {
        TargetDatabase<AdvToken> lookup = dBContext.lookup(TargetType.ADV_TOKEN);
        lookup.addNewEntry(a);
        vSaver.saveAdvTokens(getAdvTokens());
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
            AdvCard b = getAdvCard(c);
            if(b.isEnabled())
                enabled.add(c);
        }
        return enabled;
    }

    public AdvCard getAdvCard(Card card)
    {
        return getBosses().lookup(card.getID());
    }

    public AdvLocation getAdvLocation(Location loc)
    {
        return getSections().lookup(loc.getID());
    }

    private AdvToken getAdvToken(AdvToken token) {
        return getAdvTokens().lookup(token.getID());
    }

    public void toggleAdvCard(Card card) {
        AdvCard advCard = getAdvCard(card);
        advCard.setEnabled(!advCard.isEnabled());
    }

    public void toggleSection(Location loc) {
        AdvLocation s = getAdvLocation(loc);
        s.setEnabled(!loc.isEnabled());
    }

    public void toggleAdvToken(AdvToken token) {
        AdvToken t = getAdvToken(token);
        t.setEnabled(!t.isEnabled());
    }

    public List<AdvLocation> getAdvLocations() {
        TargetDatabase<AdvLocation> locations = new TargetDatabase<>();
        locations.addAll(dBContext.lookup(TargetType.LOCATION));
        return locations;
    }
}