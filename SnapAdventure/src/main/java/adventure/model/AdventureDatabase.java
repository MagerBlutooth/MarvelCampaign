package adventure.model;

import adventure.model.thing.AdvCard;
import adventure.model.thing.AdvLocation;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetList;
import snapMain.model.target.Token;

import java.util.ArrayList;

public class AdventureDatabase {

    TargetDatabase<Card> cards;
    TargetDatabase<AdvCard> advCards;
    TargetDatabase<AdvLocation> advLocations;
    TargetDatabase<Token> tokens;
    public AdventureDatabase(AdvMainDatabase controllerDatabase)
    {
        AdvMasterThingDatabase masterThingDatabase = controllerDatabase.getAdvMasterThingDatabase();
        CardList clonedCards = new CardList(new ArrayList<>());
        clonedCards = clonedCards.cloneNewList(masterThingDatabase.getEnabledCards());
        cards = new TargetDatabase<>();
        cards.addAll(clonedCards.getCards());
        advCards = masterThingDatabase.getEnabledAdvCards();
        advLocations = masterThingDatabase.getEnabledAdvLocations();
        tokens = masterThingDatabase.getEnabledTokens();
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

    public CardList getCardList()
    {
        CardList cardsCopy = new CardList(new ArrayList<>());
        cardsCopy.addAll(cards);
        return cardsCopy;
    }

    public PlayableDatabase getCardsAndTokens() {
        PlayableDatabase playableDatabase = new PlayableDatabase();
        playableDatabase.addAll(cards);
        playableDatabase.addAll(tokens);
        return playableDatabase;
    }

    public TargetDatabase<AdvLocation> getLocations() {
        return advLocations;
    }
}
