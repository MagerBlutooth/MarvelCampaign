package model.thing;

import model.database.ThingDatabase;
import model.sortFilter.CardSortMode;
import model.sortFilter.CardSorter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static model.constants.CampaignConstants.STRING_SEPARATOR;

public class CardList extends ThingList<Card> {

    CardSorter cardSorter = new CardSorter();

    public CardList(List<Card> cards)
    {
        super(cards);
    }

    public CardList(CardList cards)
    {
        super(cards);
        cardSorter = cards.cardSorter;
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

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Card c: getCards())
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromString(String cardString, ThingDatabase<Card> database)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        String[] cardsList = decodedString.split(STRING_SEPARATOR);

        for(String c: cardsList)
        {
            this.add(database.lookup(Integer.parseInt(c)));
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

    public CardList fromCardList(String cardList, ThingDatabase<Card> database)
    {
        String[] cardsList = cardList.split("\n");
        CardList cards = new CardList(new ArrayList<>());
        for(String c: cardsList)
        {
            cards.add(database.lookup(c));
        }
        return cards;
    }
}
