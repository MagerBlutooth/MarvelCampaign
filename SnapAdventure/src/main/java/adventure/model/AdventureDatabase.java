package adventure.model;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.model.target.PlayableDatabase;
import adventure.model.target.base.AdvCard;
import adventure.model.target.base.AdvCardList;
import adventure.model.target.base.AdvLocation;
import adventure.model.target.base.AdvToken;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.model.target.Token;

import java.util.ArrayList;
import java.util.Base64;

public class AdventureDatabase {

    //This class keeps track of what cards, locations, and tokens are enabled for the current adventure.
    TargetDatabase<AdvCard> advCards;
    TargetDatabase<AdvLocation> advLocations;
    TargetDatabase<AdvToken> advTokens;

    public AdventureDatabase() {
        advCards = new TargetDatabase<>();
        advLocations = new TargetDatabase<>();
        advTokens = new TargetDatabase<>();
    }

    public void createNewDatabase(AdvMainDatabase controllerDatabase) {
        AdvMasterThingDatabase masterThingDatabase = controllerDatabase.getAdvMasterThingDatabase();
        advCards = masterThingDatabase.getEnabledAdvCards();
        advLocations = masterThingDatabase.getEnabledAdvLocations();
        advTokens = masterThingDatabase.getEnabledAdvTokens();
    }

    public ActiveCardList generateActiveCards() {
        ActiveCardList activeCards = new ActiveCardList(new ArrayList<>());
        for(AdvCard c: advCards)
        {
            if(c.isActualThing() && c.isEnabled())
                activeCards.add(new ActiveCard(c.getCard()));
        }
        return activeCards;
    }


    public TargetDatabase<AdvCard> getAdvCards() {
        return advCards;
    }

    public TargetDatabase<AdvLocation> getSections() {
        return advLocations;
    }

    public TargetList<AdvCard> getBossList() {
        AdvCardList bossCopy = new AdvCardList(new ArrayList<>());
        bossCopy.addAll(advCards);
        return bossCopy;
    }

    public void fromSaveString(String saveString, AdvMainDatabase mainDatabase) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] databaseString = decodedString.split(SnapMainConstants.CATEGORY_SEPARATOR);
        String[] cardIDList = databaseString[0].split(SnapMainConstants.CSV_SEPARATOR);
        String[] locIDList = databaseString[1].split(SnapMainConstants.CSV_SEPARATOR);
        String[] tokenIDList = databaseString[2].split(SnapMainConstants.CSV_SEPARATOR);

        TargetDatabase<AdvCard> advCardDatabase = mainDatabase.lookupDatabase(TargetType.ADV_CARD);
            for(String s: cardIDList)
            {
                AdvCard a = advCardDatabase.lookup(Integer.parseInt(s));
                advCards.add(a);
            }
        TargetDatabase<AdvLocation> locDatabase = mainDatabase.lookupDatabase(TargetType.LOCATION);
        for(String s: locIDList)
        {
            AdvLocation l = locDatabase.lookup(Integer.parseInt(s));
            advLocations.add(l);
        }
        TargetDatabase<AdvToken> tokenDatabase = mainDatabase.lookupDatabase(TargetType.ADV_TOKEN);
        for(String i: tokenIDList)
        {
            AdvToken t = tokenDatabase.lookup(Integer.parseInt(i));
            advTokens.add(t);
        }
    }

    public String toSaveString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AdvCard c : advCards) {
            stringBuilder.append(c.toSaveString()).append(SnapMainConstants.CSV_SEPARATOR);
        }
        if (advCards.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(SnapMainConstants.CATEGORY_SEPARATOR);
        for (AdvLocation l : advLocations) {
            stringBuilder.append(l.getID()).append(SnapMainConstants.CSV_SEPARATOR);
        }
        if (advLocations.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(SnapMainConstants.CATEGORY_SEPARATOR);
        for (AdvToken t : advTokens) {
            stringBuilder.append(t.getID()).append(SnapMainConstants.CSV_SEPARATOR);
        }
        if (advTokens.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public TargetDatabase<Card> getCards() {
        TargetDatabase<Card> cards = new TargetDatabase<>();
        for(AdvCard aCard: getAdvCards())
        {
            if(aCard.isActualThing()) {
                Card card = aCard.getCard();
                cards.add(card);
            }
        }
        return cards;
    }

    public TargetDatabase<Token> getTokens()
    {
        TargetDatabase<Token> tokens = new TargetDatabase<>();
        for(AdvToken a: advTokens)
        {
            Token t = a.getToken();
            tokens.add(t);
        }
        return tokens;
    }

    public PlayableDatabase getEnemySubjects()
    {
        PlayableDatabase db = new PlayableDatabase();
        db.addAll(advCards.getActualThings());
        db.addAll(advTokens.getActualThings());
        return db;
    }

    public TargetDatabase<AdvToken> getAdvTokens() {
        return advTokens;
    }
}
