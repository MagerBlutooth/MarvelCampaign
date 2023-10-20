package adventure.model.target.base;

import adventure.model.sorter.SectionSortMode;
import adventure.model.sorter.SectionSorter;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.TargetList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AdvLocationList extends TargetList<AdvLocation> {

    SectionSorter SectionSorter = new SectionSorter();

    public AdvLocationList(List<AdvLocation> sections)
    {
        super(sections);
    }

    public AdvLocationList(AdvLocationList sections)
    {
        super(sections.getLocations());
        SectionSorter = sections.SectionSorter;
    }

    public void sort()
    {
        List<AdvLocation> sortedLocations = SectionSorter.sort(new ArrayList<>(getLocations()));
        this.clear();
        this.addAll(sortedLocations);
    }
    public void setSortMode(String m) {
        SectionSorter.changeMode(SectionSortMode.parseString(m));
    }

    public List<AdvLocation> getLocations() {
        return getThings();
    }

    public String toCSVSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(AdvLocation b: getLocations())
        {
            stringBuilder.append(b.getID());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(AdvLocation c: getLocations())
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, TargetDatabase<AdvLocation> database)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        String[] cardsList = decodedString.split(SnapMainConstants.STRING_SEPARATOR);

        for(String c: cardsList)
        {
            this.add(database.lookup(Integer.parseInt(c)));
        }
    }

    @Override
    public void clear()
    {
        getThings().clear();
    }

    public AdvLocationList cloneNewList(List<AdvLocation> existingList) {
        AdvLocationList clonedLocs = new AdvLocationList(new ArrayList<>());
        for(AdvLocation l: existingList)
        {
            clonedLocs.add(new AdvLocation(l));
        }
        return clonedLocs;
    }
}
