package adventure.model.sorter;

import adventure.model.target.ActiveCard;
import snapMain.model.sortFilter.CardSortMode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActiveCardSorter {

    CardSortMode cardSortMode;

    public ActiveCardSorter()
    {
        cardSortMode = CardSortMode.COST;
    }

    public List<ActiveCard> sort(List<ActiveCard> cards)
    {
        List<ActiveCard> sortedCards = new ArrayList<>(cards);
        switch(cardSortMode)
        {
            case NAME:
                sortedCards.sort((o1, o2) -> Comparator.comparing(ActiveCard::getName).compare(o1, o2));
                break;
            case COST:
                sortedCards.sort((o1, o2) -> Comparator.comparing(ActiveCard::getCost).thenComparing(ActiveCard::getPower).thenComparing(ActiveCard::getName).compare(o1, o2));
                break;
            case POOL:
                sortedCards.sort((o1, o2) -> Comparator.comparing(ActiveCard::getPool).thenComparing(ActiveCard::getCost).thenComparing(ActiveCard::getPower).compare(o1, o2));
                break;
            case POWER:
                sortedCards.sort((o1, o2) -> Comparator.comparing(ActiveCard::getPower).thenComparing(ActiveCard::getName).compare(o1, o2));
                break;
        }
        return sortedCards;
    }

    public void changeMode(CardSortMode c)
    {
        cardSortMode = c;
    }
}
