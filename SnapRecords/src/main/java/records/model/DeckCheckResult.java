package records.model;

import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import java.util.List;

public class DeckCheckResult {

    CardList invalidCards;
    boolean result;


    public DeckCheckResult(boolean r)
    {
        result = r;
        invalidCards = new CardList();
    }
    public DeckCheckResult(CardList cardList, boolean r)
    {
        invalidCards = cardList;
        result = r;
    }

    public boolean getResult() {
        return result;
    }

    public CardList getInvalidCards() {
        return invalidCards;
    }

    public void setResult(boolean r) {
        result = r;
    }

    public void setInvalidCards(List<Card> c) {
        invalidCards.addAll(c);
    }
}
