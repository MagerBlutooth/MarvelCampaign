package adventure.model;

import campaign.controller.ControllerDatabase;
import campaign.model.database.MasterThingDatabase;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.*;

import java.util.ArrayList;
import java.util.List;

public class AdvControllerDatabase extends ControllerDatabase {

    AdvMasterThingDatabase advMasterThingDatabase;

    public AdvControllerDatabase(MasterThingDatabase database)
    {
        super(database);
        advMasterThingDatabase = new AdvMasterThingDatabase(database);
        advMasterThingDatabase.loadDatabase();
    }

    public List<Card> getCards() {
        return advMasterThingDatabase.getCards();
    }

    public void saveDatabase(ThingType thingType) {
        advMasterThingDatabase.saveDatabase(thingType);
    }

    public <T extends Thing> ThingDatabase<T> lookupDatabase(ThingType type) {
        return advMasterThingDatabase.lookupDatabase(type);
    }

    public List<Boss> getBosses() {
        return advMasterThingDatabase.getBosses();
    }
    public List<Section> getSections() {
        return advMasterThingDatabase.getSections();
    }

    public AdvMasterThingDatabase getAdvMasterThingDatabase() {
        return advMasterThingDatabase;
    }

    public List<Boss> getEnabledBosses()
    {
        List<Boss> enabledBosses = new ArrayList<>();
        for(Boss b: getBosses())
        {
            if(b.isEnabled())
                enabledBosses.add(b);
        }
        return enabledBosses;
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

    public List<Section> getEnabledSections() {
        List<Section> enabledLocs = new ArrayList<>();
        for(Section s: getSections())
        {
            if(s.isEnabled())
                enabledLocs.add(s);
        }
        return enabledLocs;
    }

    public void modifyBoss(Boss b) {
        advMasterThingDatabase.modifyBoss(b);
    }
    public void modifySection(Section s) {
        advMasterThingDatabase.modifySection(s);
    }

    public void toggleBoss(Card c) {
        advMasterThingDatabase.toggleBoss(c);
    }

    public Boss getBoss(Card card) {
        return advMasterThingDatabase.getBoss(card);
    }

    public Section getSection(Location location) {
        return advMasterThingDatabase.getSection(location);
    }
    public void toggleSection(Location loc) {
        advMasterThingDatabase.toggleSection(loc);
    }
}
