package adventure.model.sorter;

import adventure.model.thing.AdvCard;
import snapMain.model.sortFilter.BossSortMode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BossSorter {

    BossSortMode bossSortMode;

    public BossSorter()
    {
        bossSortMode = BossSortMode.NAME;
    }

    public List<AdvCard> sort(List<AdvCard> advCards)
    {
        List<AdvCard> sortedCards = new ArrayList<>(advCards);
        switch(bossSortMode)
        {
            case NAME:
                sortedCards.sort((o1, o2) -> Comparator.comparing(AdvCard::getName).compare(o1, o2));
                break;
        }
        return sortedCards;
    }

    public void changeMode(BossSortMode b)
    {
        bossSortMode = b;
    }

}
