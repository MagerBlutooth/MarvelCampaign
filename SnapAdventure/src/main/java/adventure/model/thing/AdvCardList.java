package adventure.model.thing;

import adventure.model.sorter.BossSorter;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.sortFilter.BossSortMode;
import snapMain.model.target.TargetList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AdvCardList extends TargetList<AdvCard> {

    BossSorter bossSorter = new BossSorter();

    public AdvCardList(List<AdvCard> advCards)
    {
        super(advCards);
    }

    public AdvCardList(AdvCardList bosses)
    {
        super(bosses.getBosses());
        bossSorter = bosses.bossSorter;
    }

    public void sort()
    {
        List<AdvCard> sortedAdvCards = bossSorter.sort(new ArrayList<>(getBosses()));
        this.clear();
        this.addAll(sortedAdvCards);
    }
    public void setSortMode(String m) {
        bossSorter.changeMode(BossSortMode.parseString(m));
    }

    public List<AdvCard> getBosses() {
        return getThings();
    }

    public String toCSVSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(AdvCard b: getBosses())
        {
            stringBuilder.append(b.getID());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public void fromCSVSaveString(String bossString, TargetDatabase<AdvCard> database)
    {
        String[] bossList = bossString.split(SnapMainConstants.STRING_SEPARATOR);

        for(String c: bossList)
        {
            this.add(database.lookup(Integer.parseInt(c)));
        }
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(AdvCard c: getBosses())
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, TargetDatabase<AdvCard> database)
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
        for(AdvCard c: getBosses())
        {
            stringBuilder.append(c.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public AdvCardList fromBossList(String bossList, TargetDatabase<AdvCard> database)
    {
        String[] bossesList = bossList.split("\n");
        AdvCardList bosses = new AdvCardList(new ArrayList<>());
        for(String c: bossesList)
        {
            bosses.add(database.lookup(c));
        }
        return bosses;
    }

    public int getBossIndex(AdvCard b) {
        return this.indexOf(b);
    }
}
