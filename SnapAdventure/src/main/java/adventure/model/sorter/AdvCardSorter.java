package adventure.model.sorter;

import adventure.model.target.base.AdvCard;
import snapMain.model.sortFilter.AdvCardSortMode;
import snapMain.model.target.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvCardSorter {

    AdvCardSortMode advCardSortMode;

    public AdvCardSorter()
    {
        advCardSortMode = AdvCardSortMode.NAME;
    }

    public List<AdvCard> sort(List<AdvCard> advCards)
    {
        List<AdvCard> sortedCards = new ArrayList<>(advCards);
        switch(advCardSortMode)
        {
            case NAME:
                sortedCards.sort((o1, o2) -> Comparator.comparing(AdvCard::getName).compare(o1, o2));
                break;
            case COST:
                sortedCards.sort((o1, o2) -> Comparator.comparing(AdvCard::getCost).thenComparing(AdvCard::getPower).thenComparing(AdvCard::getName).compare(o1, o2));
                break;
            case POOL:
                sortedCards.sort((o1, o2) -> Comparator.comparing(AdvCard::getPool).thenComparing(AdvCard::getCost).thenComparing(AdvCard::getPower).compare(o1, o2));
                break;
            case POWER:
                sortedCards.sort((o1, o2) -> Comparator.comparing(AdvCard::getPower).thenComparing(AdvCard::getName).compare(o1, o2));
                break;
        }
        return sortedCards;
    }

    public void changeMode(AdvCardSortMode b)
    {
        advCardSortMode = b;
    }

}
