package adventure.model.sorter;

import adventure.model.target.AdvLocation;
import adventure.model.target.AdvToken;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvTokenSorter {

    AdvTokenSortMode sortMode;

    public AdvTokenSorter()
    {
        sortMode = adventure.model.sorter.AdvTokenSortMode.NAME;
    }

    public List<AdvToken> sort(List<AdvToken> advTokens)
    {
        List<AdvToken> sortedCards = new ArrayList<>(advTokens);
        switch(sortMode)
        {
            case NAME:
                sortedCards.sort((o1, o2) -> Comparator.comparing(AdvToken::getName).compare(o1, o2));
                break;
        }
        return sortedCards;
    }

    public void changeMode(AdvTokenSortMode m)
    {
        sortMode = m;
    }

}
