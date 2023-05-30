package model.sortFilter;

import model.thing.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CardSorter {

    CardSortMode cardSortMode;

    public CardSorter()
    {
        cardSortMode = CardSortMode.COST;
    }

    public List<Card> sort(List<Card> cards)
    {
        List<Card> sortedCards = new ArrayList<>(cards);
        switch(cardSortMode)
        {
            case NAME:
                sortedCards.sort((o1, o2) -> Comparator.comparing(Card::getName).compare(o1, o2));
                break;
            case COST:
                sortedCards.sort((o1, o2) -> Comparator.comparing(Card::getCost).thenComparing(Card::getPower).thenComparing(Card::getName).compare(o1, o2));
                break;
            case POOL:
                sortedCards.sort((o1, o2) -> Comparator.comparing(Card::getPool).thenComparing(Card::getCost).thenComparing(Card::getPower).compare(o1, o2));
                break;
            case POWER:
                sortedCards.sort((o1, o2) -> Comparator.comparing(Card::getPower).thenComparing(Card::getName).compare(o1, o2));
                break;
        }
        return sortedCards;
    }

    public void changeMode(CardSortMode c)
    {
        cardSortMode = c;
    }
}
