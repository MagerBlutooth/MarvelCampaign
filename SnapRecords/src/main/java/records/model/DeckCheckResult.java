package records.model;

import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import java.util.List;

public class DeckCheckResult {

    CardList invalidCards;
    boolean result;
    int uniqueCardCount;

    public DeckCheckResult(boolean r, int u)
    {
        result = r;
        invalidCards = new CardList();
        uniqueCardCount = u;
    }
    public DeckCheckResult(CardList cardList, boolean r, int u)
    {
        invalidCards = cardList;
        result = r;
        uniqueCardCount = u;
    }

    public boolean getResult() {
        return result;
    }

    public int getUniqueCardCount()
    {
        return uniqueCardCount;
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

    public void setUniqueCardCount(int u) {
        uniqueCardCount = u;
    }
}
