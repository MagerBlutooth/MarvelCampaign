package adventure.model.sorter;

import adventure.model.Section;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SectionSorter {

    SectionSortMode SectionSortMode;

    public SectionSorter()
    {
        SectionSortMode = adventure.model.sorter.SectionSortMode.NAME;
    }

    public List<Section> sort(List<Section> sections)
    {
        List<Section> sortedCards = new ArrayList<>(sections);
        switch(SectionSortMode)
        {
            case NAME:
                sortedCards.sort((o1, o2) -> Comparator.comparing(Section::getName).compare(o1, o2));
                break;
        }
        return sortedCards;
    }

    public void changeMode(SectionSortMode b)
    {
        SectionSortMode = b;
    }

}
