package records.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HallOfFameSorter {

    HallOfFameSortMode hallOfFameSortMode;

    public HallOfFameSorter()
    {
        hallOfFameSortMode = HallOfFameSortMode.ID;
    }

    public List<HallOfFameEntry> sort(List<HallOfFameEntry> hallOfFameEntries)
    {
        List<HallOfFameEntry> sortedEntries = new ArrayList<>(hallOfFameEntries);
        switch(hallOfFameSortMode)
        {
            case NAME: {
                sortedEntries.sort((o1, o2) -> Comparator.comparing(HallOfFameEntry::getName).compare(o1, o2));
                break;
            }
            case ID:
            {
                sortedEntries.sort((o1, o2) -> Comparator.comparing(HallOfFameEntry::getID).compare(o1, o2));
                break;
            }
        }
        return sortedEntries;
    }

    public void changeMode(HallOfFameSortMode d)
    {
        hallOfFameSortMode = d;
    }
}
