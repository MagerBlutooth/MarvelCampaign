package snapMain.model.target;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.sortFilter.CardFilter;
import snapMain.model.sortFilter.CardSortMode;
import snapMain.model.sortFilter.CardSorter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CardList extends TargetList<Card> {

    CardSorter cardSorter = new CardSorter();
    CardFilter cardFilter = new CardFilter();

    public CardList(List<Card> cards)
    {
        super(cards);
    }

    public CardList(CardList cards)
    {
        super(cards);
        cardSorter = cards.cardSorter;
        cardFilter = cards.cardFilter;
    }

    public CardList() {
        super();
    }

    public void sort()
    {
        List<Card> sortedCards = cardSorter.sort(new ArrayList<>(getCards()));
        this.clear();
        this.addAll(sortedCards);
    }
    public void setSortMode(String m) {
        cardSorter.changeMode(CardSortMode.parseString(m));
    }

    public List<Card> getCards() {
        return getThings();
    }

    public String toCSVSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Card c: getCards())
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public void fromCSVSaveString(String cardString, TargetDatabase<Card> database)
    {
        String[] cardsList = cardString.split(SnapMainConstants.STRING_SEPARATOR);

        for(String c: cardsList)
        {
            this.add(database.lookup(Integer.parseInt(c)));
        }
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Card c: getCards())
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(SnapMainConstants.CSV_SEPARATOR);
        }
        if(!getCards().isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        else
            stringBuilder.append(" ");
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, TargetDatabase<Card> database)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] cardsList = decodedString.split(SnapMainConstants.STRING_SEPARATOR);

        for(String cString: cardsList)
        {
            String[] cOptions = cString.split(SnapMainConstants.CSV_SEPARATOR);
            Card card = new Card(database.lookup(Integer.parseInt(cOptions[0])));
            this.add(card);
        }
    }

    @Override
    public void clear()
    {
        things.clear();
    }

    public String toCardListString() {
        StringBuilder stringBuilder = new StringBuilder();
        sort();
        for(Card c: getCards())
        {
            stringBuilder.append(c.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String toString()
    {
        if(this.isEmpty())
            return "Empty";
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < size()-1; i++)
        {
            s.append(get(i).getName());
            s.append(", ");
        }
        s.append(this.get(size()-1).getName());
        return s.toString();
    }

    public int getCardIndex(Card captain) {
        return this.indexOf(captain);
    }

    public CardList cloneNewList(List<Card> existingList) {
        CardList clonedCards = new CardList(new ArrayList<>());
        for(Card c: existingList)
        {
            clonedCards.add(new Card(c));
        }
        return clonedCards;
    }

    public CardList filterCost(int minCost, int maxCost) {
        return cardFilter.filterCost(this, minCost, maxCost);
    }

    public CardList filterPool(int minPool, int maxPool)
    {
        return cardFilter.filterPool(this, minPool, maxPool);
    }
    public CardList filterPower(int minPower, int maxPower)
    {
        return cardFilter.filterPower(this, minPower, maxPower);
    }

    public CardList filterAttributes(List<CardAttribute> cardAttributes)
    {
        return cardFilter.filterAttributes(this, cardAttributes);
    }
}
