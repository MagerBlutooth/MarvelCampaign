package adventure.model.target;

import adventure.model.sorter.ActiveCardFilter;
import adventure.model.sorter.ActiveCardSorter;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.sortFilter.CardSortMode;
import snapMain.model.target.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ActiveCardList extends TargetList<ActiveCard> {

    ActiveCardSorter cardSorter = new ActiveCardSorter();
    ActiveCardFilter cardFilter = new ActiveCardFilter();

    public ActiveCardList()
    {
        super();
    }

    public ActiveCardList(List<ActiveCard> activeCards)
    {
        super(activeCards);
    }

    public ActiveCardList(ActiveCardList cards)
    {
        super(cards.getThings());
        cardSorter = cards.cardSorter;
        cardFilter = cards.cardFilter;
    }

    public void sort()
    {
        List<ActiveCard> sortedBaseCards = cardSorter.sort(getThings());
        this.clear();
        this.addAll(sortedBaseCards);
    }

    public void setSortMode(String m) {
        cardSorter.changeMode(CardSortMode.parseString(m));
    }

    public String toCSVSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(ActiveCard c: getThings())
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(ActiveCard c: getThings())
        {
            stringBuilder.append(c.toSaveString());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        if(!stringBuilder.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        else
            stringBuilder.append(" ");
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, TargetDatabase<Card> cards)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        String[] cardsList = decodedString.split(SnapMainConstants.STRING_SEPARATOR);
        if(decodedString.isBlank())
            return;
        for(String c: cardsList)
        {
            ActiveCard a = new ActiveCard();
            a.fromSaveString(c, cards);
            this.add(a);
        }
    }

    public ActiveCardList filterCost(int minCost, int maxCost) {
        return cardFilter.filterCost(this, minCost, maxCost);
    }

    public ActiveCardList filterPool(int minPool, int maxPool)
    {
        return cardFilter.filterPool(this, minPool, maxPool);
    }
    public ActiveCardList filterPower(int minPower, int maxPower)
    {
        return cardFilter.filterPower(this, minPower, maxPower);
    }

    public ActiveCardList filterAttributes(List<CardAttribute> cardAttributes)
    {
        return cardFilter.filterAttributes(this, cardAttributes);
    }

    @Override
    public void clear()
    {
        getThings().clear();
    }

    public ActiveCardList cloneNewList(List<ActiveCard> existingList) {
        ActiveCardList clonedCards = new ActiveCardList();
        for(ActiveCard c: existingList)
        {
            clonedCards.add(new ActiveCard(c));
        }
        return clonedCards;
    }

    public CardList getBaseCards() {
        CardList baseCards = new CardList();
        for(ActiveCard c: this)
        {
            baseCards.add(c.getCard());
        }
        return baseCards;
    }

    public ActiveCard lookupActiveCard(int id) {
        for(ActiveCard c: this)
        {
            if(c.getID() == id)
                return c;
        }
        return new ActiveCard(new Card());
    }

    public List<ActiveCard> getActiveThings() {
        List<ActiveCard> activeCards = new ArrayList<>();
        for(ActiveCard c: this)
        {
            if(!c.hasStatus(StatusEffect.EXHAUSTED) && !c.hasStatus(StatusEffect.PIG) &&
                    !c.hasStatus(StatusEffect.RAPTOR))
                activeCards.add(c);
        }
        return activeCards;
    }

    public boolean hasNoCaptains() {
        for(ActiveCard c: this)
        {
            if(c.hasStatus(StatusEffect.CAPTAIN))
            {
                return false;
            }
        }
        return true;
    }

    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Deck: ");
        stringBuilder.append("[");
        for(ActiveCard c: this)
        {
            stringBuilder.append(c);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public TargetList<ActiveCard> getCaptains() {
        TargetList<ActiveCard> captains = new ActiveCardList();
        for(ActiveCard c: this)
        {
            if(c.hasStatus(StatusEffect.CAPTAIN))
            {
                captains.add(c);
            }
        }
        return captains;
    }
}
