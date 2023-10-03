package adventure.model;

import adventure.model.thing.AdvCard;
import adventure.model.thing.AdvLocation;
import snapMain.controller.MainDatabase;
import snapMain.model.database.MasterThingDatabase;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;

import java.util.ArrayList;
import java.util.List;

public class AdvMainDatabase extends MainDatabase {

    AdvMasterThingDatabase advMasterThingDatabase;

    public AdvMainDatabase(MasterThingDatabase database)
    {
        super(database);
        advMasterThingDatabase = new AdvMasterThingDatabase(database);
        advMasterThingDatabase.loadDatabase();
    }

    public List<Card> getCards() {
        return advMasterThingDatabase.getCards();
    }
    public List<Token> getTokens()
    {
        return advMasterThingDatabase.getTokens();
    }

    public void saveDatabase(TargetType targetType) {
        advMasterThingDatabase.saveDatabase(targetType);
    }

    public <T extends Target> TargetDatabase<T> lookupDatabase(TargetType type) {
        return advMasterThingDatabase.lookupDatabase(type);
    }

    public List<AdvCard> getBosses() {
        return advMasterThingDatabase.getBosses();
    }
    public List<AdvLocation> getSections() {
        return advMasterThingDatabase.getSections();
    }

    public AdvMasterThingDatabase getAdvMasterThingDatabase() {
        return advMasterThingDatabase;
    }

    public List<AdvCard> getEnabledBosses()
    {
        List<AdvCard> enabledAdvCards = new ArrayList<>();
        for(AdvCard b: getBosses())
        {
            if(b.isEnabled())
                enabledAdvCards.add(b);
        }
        return enabledAdvCards;
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

    public List<AdvLocation> getEnabledSections() {
        List<AdvLocation> enabledLocs = new ArrayList<>();
        for(AdvLocation s: getSections())
        {
            if(s.isEnabled())
                enabledLocs.add(s);
        }
        return enabledLocs;
    }

    public void modifyBoss(AdvCard b) {
        advMasterThingDatabase.modifyBoss(b);
    }
    public void modifySection(AdvLocation s) {
        advMasterThingDatabase.modifySection(s);
    }

    public void toggleBoss(Card c) {
        advMasterThingDatabase.toggleBoss(c);
    }

    public AdvCard getBoss(Card card) {
        return advMasterThingDatabase.getBoss(card);
    }

    public AdvLocation getSection(Location location) {
        return advMasterThingDatabase.getSection(location);
    }
    public void toggleSection(Location loc) {
        advMasterThingDatabase.toggleSection(loc);
    }

    public PlayableDatabase getCardsAndTokens() {
        PlayableDatabase playableDatabase = new PlayableDatabase();
        playableDatabase.addAll(getCards());
        playableDatabase.addAll(getTokens());
        return playableDatabase;
    }
}
