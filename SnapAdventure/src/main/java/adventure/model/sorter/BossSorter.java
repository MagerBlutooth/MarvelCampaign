package adventure.model.sorter;

import adventure.model.Boss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BossSorter {

    BossSortMode bossSortMode;

    public BossSorter()
    {
        bossSortMode = BossSortMode.NAME;
    }

    public List<Boss> sort(List<Boss> bosses)
    {
        List<Boss> sortedCards = new ArrayList<>(bosses);
        switch(bossSortMode)
        {
            case NAME:
                sortedCards.sort((o1, o2) -> Comparator.comparing(Boss::getName).compare(o1, o2));
                break;
        }
        return sortedCards;
    }

    public void changeMode(BossSortMode b)
    {
        bossSortMode = b;
    }

}
