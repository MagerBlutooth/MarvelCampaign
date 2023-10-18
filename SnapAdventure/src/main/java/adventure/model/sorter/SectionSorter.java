package adventure.model.sorter;

import adventure.model.target.AdvLocation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SectionSorter {

    SectionSortMode SectionSortMode;

    public SectionSorter()
    {
        SectionSortMode = adventure.model.sorter.SectionSortMode.NAME;
    }

    public List<AdvLocation> sort(List<AdvLocation> advLocations)
    {
        List<AdvLocation> sortedCards = new ArrayList<>(advLocations);
        switch(SectionSortMode)
        {
            case NAME:
                sortedCards.sort((o1, o2) -> Comparator.comparing(AdvLocation::getName).compare(o1, o2));
                break;
        }
        return sortedCards;
    }

    public void changeMode(SectionSortMode b)
    {
        SectionSortMode = b;
    }

}
