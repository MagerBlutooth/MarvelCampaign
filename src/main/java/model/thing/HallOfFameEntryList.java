package model.thing;

import model.database.ThingDatabase;
import model.sortFilter.HallOfFameSortMode;
import model.sortFilter.HallOfFameSorter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static model.constants.CampaignConstants.STRING_SEPARATOR;

public class HallOfFameEntryList extends ThingList<HallOfFameEntry> {

    HallOfFameSorter hallOfFameSorter;

    public HallOfFameEntryList(List<HallOfFameEntry> entries)
    {
        super(entries);
        hallOfFameSorter = new HallOfFameSorter();
    }

    public HallOfFameEntryList(HallOfFameEntryList entries)
    {
        super(entries);
        hallOfFameSorter = entries.getSorter();
    }

    private HallOfFameSorter getSorter() {
        return hallOfFameSorter;
    }

    public void sort()
    {
        List<HallOfFameEntry> hallOfFameEntries = hallOfFameSorter.sort(new ArrayList<>(getEntries()));
        this.clear();
        this.addAll(hallOfFameEntries);
    }
    public void setSortMode(String m) {
        hallOfFameSorter.changeMode(HallOfFameSortMode.parseString(m));
    }

    public List<HallOfFameEntry> getEntries() {
        return getThings();
    }

    public String toCSVSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(HallOfFameEntry e: getEntries())
        {
            stringBuilder.append(e.getID());
            stringBuilder.append(STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public void fromCSVSaveString(String cardString, ThingDatabase<HallOfFameEntry> database)
    {
        String[] entriesList = cardString.split(STRING_SEPARATOR);

        for(String c: entriesList)
        {
            this.add(database.lookup(Integer.parseInt(c)));
        }
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(HallOfFameEntry e: getEntries())
        {
            stringBuilder.append(e.getID());
            stringBuilder.append(STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, ThingDatabase<HallOfFameEntry> database)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        String[] hallOfFame = decodedString.split(STRING_SEPARATOR);

        for(String e: hallOfFame)
        {
            this.add(database.lookup(Integer.parseInt(e)));
        }
    }

    @Override
    public void clear()
    {
        things.clear();
    }

    public String toCardListString() {
        StringBuilder stringBuilder = new StringBuilder();
        sort();
        for(HallOfFameEntry e: getEntries())
        {
            stringBuilder.append(e.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public HallOfFameEntryList fromCardList(String cardList, ThingDatabase<HallOfFameEntry> database)
    {
        String[] cardsList = cardList.split("\n");
        HallOfFameEntryList entries = new HallOfFameEntryList(new ArrayList<>());
        for(String e: cardsList)
        {
            entries.add(database.lookup(e));
        }
        return entries;
    }
}
