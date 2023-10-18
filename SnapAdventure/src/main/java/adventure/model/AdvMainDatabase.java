package adventure.model;

import adventure.model.target.*;
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
    public TargetDatabase<Token> getTokens()
    {
        return advMasterThingDatabase.getTokens();
    }

    public void saveDatabase(TargetType targetType) {
        advMasterThingDatabase.saveDatabase(targetType);
    }

    public <T extends SnapTarget> TargetDatabase<T> lookupDatabase(TargetType type) {
        return advMasterThingDatabase.lookupDatabase(type);
    }

    public List<AdvCard> getBosses() {
        return advMasterThingDatabase.getBosses();
    }
    public List<AdvCard> getActualAdvCards() {
        List<AdvCard> bosses = advMasterThingDatabase.getBosses();
        List<AdvCard> actualBosses = new ArrayList<>();
        for(AdvCard a: bosses)
        {
            if(a.isActualThing())
                actualBosses.add(a);
        }
        return actualBosses;
    }

    public List<AdvLocation> getActualAdvLocations() {
        List<AdvLocation> locations = advMasterThingDatabase.getAdvLocations();
        List<AdvLocation> actualLocations = new ArrayList<>();
        for(AdvLocation a: locations)
        {
            if(a.isActualThing())
                actualLocations.add(a);
        }
        return actualLocations;
    }

    public List<AdvToken> getActualAdvTokens() {
        List<AdvToken> tokens = advMasterThingDatabase.getAdvTokens();
        List<AdvToken> actualTokens = new ArrayList<>();
        for(AdvToken a: tokens)
        {
            if(a.isActualThing())
                actualTokens.add(a);
        }
        return actualTokens;
    }

    public List<AdvLocation> getSections() {
        return advMasterThingDatabase.getSections();
    }

    public TargetDatabase<AdvToken> getAdvTokens() {
        return advMasterThingDatabase.getAdvTokens();
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
    public void modifyBoss(AdvCard b) {
        advMasterThingDatabase.modifyBoss(b);
    }
    public void modifySection(AdvLocation s) {
        advMasterThingDatabase.modifySection(s);
    }
    public void modifyAdvToken(AdvToken a) {
        advMasterThingDatabase.modifyAdvToken(a);
    }

    public void toggleAdvCard(Card c) {
        advMasterThingDatabase.toggleAdvCard(c);
    }

    public void toggleSection(Location loc) {
        advMasterThingDatabase.toggleSection(loc);
    }
    public void toggleAdvToken(AdvToken token)
    {
        advMasterThingDatabase.toggleAdvToken(token);
    }

    public PlayableDatabase getCardsAndTokens() {
        PlayableDatabase playableDatabase = new PlayableDatabase();
        playableDatabase.addAll(getCards());
        playableDatabase.addAll(getTokens());
        return playableDatabase;
    }
}
