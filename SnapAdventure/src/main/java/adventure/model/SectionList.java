package adventure.model;

import adventure.model.sorter.SectionSortMode;
import adventure.model.sorter.SectionSorter;
import campaign.model.constants.CampaignConstants;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.ThingList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SectionList extends ThingList<Section> {

    SectionSorter SectionSorter = new SectionSorter();

    public SectionList(List<Section> sectiones)
    {
        super(sectiones);
    }

    public SectionList(SectionList Sectiones)
    {
        super(Sectiones.getSectiones());
        SectionSorter = Sectiones.SectionSorter;
    }

    public void sort()
    {
        List<Section> sortedSectiones = SectionSorter.sort(new ArrayList<>(getSectiones()));
        this.clear();
        this.addAll(sortedSectiones);
    }
    public void setSortMode(String m) {
        SectionSorter.changeMode(SectionSortMode.parseString(m));
    }

    public List<Section> getSectiones() {
        return getThings();
    }

    public String toCSVSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Section b: getSectiones())
        {
            stringBuilder.append(b.getID());
            stringBuilder.append(CampaignConstants.STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public void fromCSVSaveString(String SectionString, ThingDatabase<Section> database)
    {
        String[] SectionList = SectionString.split(CampaignConstants.STRING_SEPARATOR);

        for(String c: SectionList)
        {
            this.add(database.lookup(Integer.parseInt(c)));
        }
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Section c: getSectiones())
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(CampaignConstants.STRING_SEPARATOR);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, ThingDatabase<Section> database)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        String[] cardsList = decodedString.split(CampaignConstants.STRING_SEPARATOR);

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
        for(Section c: getSectiones())
        {
            stringBuilder.append(c.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public SectionList fromSectionList(String SectionList, ThingDatabase<Section> database)
    {
        String[] SectionesList = SectionList.split("\n");
        SectionList Sectiones = new SectionList(new ArrayList<>());
        for(String c: SectionesList)
        {
            Sectiones.add(database.lookup(c));
        }
        return Sectiones;
    }

    public int getSectionIndex(Section b) {
        return this.indexOf(b);
    }
}
