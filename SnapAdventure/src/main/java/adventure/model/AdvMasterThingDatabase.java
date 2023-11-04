package adventure.model;

import adventure.model.target.base.*;
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
        dBContext.register(TargetType.ADV_TOKEN, vFactory.loadAdvTokens(tokens));
    }

    public TargetDatabase<AdvCard> getAdvCards() {
        return (TargetDatabase<AdvCard>) dBContext.lookup(TargetType.ADV_CARD);
    }

    public TargetDatabase<AdvLocation> getAdvLocations() {
        return (TargetDatabase<AdvLocation>) dBContext.lookup(TargetType.LOCATION);
    }

    public TargetDatabase<AdvToken> getAdvTokens() {
        return (TargetDatabase<AdvToken>) dBContext.lookup(TargetType.ADV_TOKEN);
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

    public void modifyBoss(AdvCard b) {
        TargetDatabase<AdvCard> lookup = dBContext.lookup(TargetType.ADV_CARD);
        lookup.addNewEntry(b);
        vSaver.saveBosses(getAdvCards());
    }

    public void modifySection(AdvLocation s)
    {
        TargetDatabase<AdvLocation> lookup = dBContext.lookup(TargetType.LOCATION);
        lookup.addNewEntry(s);
        vSaver.saveSections(getAdvLocations());
    }

    public void modifyAdvToken(AdvToken a) {
        TargetDatabase<AdvToken> lookup = dBContext.lookup(TargetType.ADV_TOKEN);
        lookup.addNewEntry(a);
        vSaver.saveAdvTokens(getAdvTokens());
    }

    public TargetDatabase<AdvCard> getEnabledAdvCards() {
        TargetDatabase<AdvCard> enabled = new TargetDatabase<>();
        for(AdvCard b: getAdvCards())
        {
            if(b.isEnabled() && b.isActualThing())
                enabled.add(b);
        }
        return enabled;
    }

    public TargetDatabase<AdvLocation> getEnabledAdvLocations() {
        TargetDatabase<AdvLocation> enabled = new TargetDatabase<>();
        for(AdvLocation s: getAdvLocations())
        {
            if(s.isEnabled() && s.isActualThing())
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
            if(b.isEnabled() && b.isActualThing())
                enabled.add(c);
        }
        return enabled;
    }

    public TargetDatabase<AdvToken> getEnabledAdvTokens() {
        TargetDatabase<AdvToken> enabled = new TargetDatabase<>();
        for(AdvToken a: getAdvTokens())
        {
            if(a.isEnabled() && a.isActualThing())
                enabled.add(a);
        }
        return enabled;
    }

    public AdvCard getAdvCard(Card card)
    {
        return getAdvCards().lookup(card.getID());
    }

    public AdvLocation getAdvLocation(Location loc)
    {
        return getAdvLocations().lookup(loc.getID());
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
}