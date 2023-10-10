package adventure.model.thing;

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

    public AdvLocationList(List<AdvLocation> sectiones)
    {
        super(sectiones);
    }

    public AdvLocationList(AdvLocationList Sectiones)
    {
        super(Sectiones.getLocations());
        SectionSorter = Sectiones.SectionSorter;
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

    public void fromCSVSaveString(String SectionString, TargetDatabase<AdvLocation> database)
    {
        String[] SectionList = SectionString.split(SnapMainConstants.STRING_SEPARATOR);

        for(String c: SectionList)
        {
            this.add(database.lookup(Integer.parseInt(c)));
        }
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

    public String toCardListString() {
        StringBuilder stringBuilder = new StringBuilder();
        sort();
        for(AdvLocation c: getLocations())
        {
            stringBuilder.append(c.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public AdvLocationList fromSectionList(String SectionList, TargetDatabase<AdvLocation> database)
    {
        String[] SectionesList = SectionList.split("\n");
        AdvLocationList Sectiones = new AdvLocationList(new ArrayList<>());
        for(String c: SectionesList)
        {
            Sectiones.add(database.lookup(c));
        }
        return Sectiones;
    }

    public int getSectionIndex(AdvLocation b) {
        return this.indexOf(b);
    }
}
