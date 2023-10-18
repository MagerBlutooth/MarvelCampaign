package adventure.model;

import adventure.model.adventure.Adventure;
import adventure.model.target.AdvCard;
import adventure.model.target.AdvCardList;
import adventure.model.target.AdvLocation;
import adventure.model.target.AdvToken;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;

import java.util.ArrayList;
import java.util.Base64;

public class AdventureDatabase {

    //This class keeps track of what cards, locations, and tokens are enabled for the current adventure.
    TargetDatabase<Card> cards;
    TargetDatabase<AdvCard> advCards;
    TargetDatabase<AdvLocation> advLocations;
    TargetDatabase<AdvToken> advTokens;

    public AdventureDatabase() {
        cards = new TargetDatabase<>();
        advCards = new TargetDatabase<>();
        advLocations = new TargetDatabase<>();
        advTokens = new TargetDatabase<>();
    }

    public void createNewDatabase(AdvMainDatabase controllerDatabase) {
        AdvMasterThingDatabase masterThingDatabase = controllerDatabase.getAdvMasterThingDatabase();
        CardList clonedCards = new CardList(new ArrayList<>());
        clonedCards = clonedCards.cloneNewList(masterThingDatabase.getEnabledCards());
        cards = new TargetDatabase<>();
        cards.addAll(clonedCards.getCards());
        advCards = masterThingDatabase.getEnabledAdvCards();
        advLocations = masterThingDatabase.getEnabledAdvLocations();
        advTokens = masterThingDatabase.getEnabledAdvTokens();
    }


    public TargetDatabase<AdvCard> getBosses() {
        return advCards;
    }

    public TargetDatabase<AdvLocation> getSections() {
        return advLocations;
    }

    public TargetDatabase<Card> getCards() {
        return cards;
    }

    public PlayableDatabase getCardsAndTokens() {
        PlayableDatabase playableDatabase = new PlayableDatabase();
        playableDatabase.addAll(cards);
        playableDatabase.addAll(advTokens);
        return playableDatabase;
    }

    public TargetList<AdvCard> getBossList() {
        AdvCardList bossCopy = new AdvCardList(new ArrayList<>());
        bossCopy.addAll(advCards);
        return bossCopy;
    }

    public TargetList<Card> getCardList() {
        CardList c = new CardList(new ArrayList<>());
        c.addAll(cards);
        return c;
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

        TargetDatabase<Card> cardDatabase = mainDatabase.lookupDatabase(TargetType.CARD);
        TargetDatabase<AdvCard> advCardDatabase = mainDatabase.lookupDatabase(TargetType.ADV_CARD);
            for(String i: cardIDList)
            {
                Card c = cardDatabase.lookup(Integer.parseInt(i));
                cards.add(c);
                AdvCard a = advCardDatabase.lookup(Integer.parseInt(i));
                advCards.add(a);
            }
        TargetDatabase<AdvLocation> locDatabase = mainDatabase.lookupDatabase(TargetType.LOCATION);
        for(String i: locIDList)
        {
            AdvLocation l = locDatabase.lookup(Integer.parseInt(i));
            advLocations.add(l);
        }
        TargetDatabase<AdvToken> tokenDatabase = mainDatabase.lookupDatabase(TargetType.TOKEN);
        for(String i: tokenIDList)
        {
            AdvToken t = tokenDatabase.lookup(Integer.parseInt(i));
            advTokens.add(t);
        }
    }

    public String toSaveString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card c : cards) {
            stringBuilder.append(c.getID()).append(SnapMainConstants.CSV_SEPARATOR);
        }
        if (cards.isEmpty())
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
}
