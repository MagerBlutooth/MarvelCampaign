package adventure.model;

import campaign.model.sortFilter.BossSortMode;
import adventure.model.sorter.BossSorter;
import campaign.model.constants.CampaignConstants;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class BossList extends ThingList<Boss> {

    BossSorter bossSorter = new BossSorter();

    public BossList(List<Boss> bosses)
    {
        super(bosses);
    }

    public BossList(BossList bosses)
    {
        super(bosses.getBosses());
        bossSorter = bosses.bossSorter;
    }

    public void sort()
    {
        List<Boss> sortedBosses = bossSorter.sort(new ArrayList<>(getBosses()));
        this.clear();
        this.addAll(sortedBosses);
    }
    public void setSortMode(String m) {
        bossSorter.changeMode(BossSortMode.parseString(m));
    }

    public List<Boss> getBosses() {
        return getThings();
    }

    public String toCSVSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Boss b: getBosses())
        {
            stringBuilder.append(b.getID());
            stringBuilder.append(CampaignConstants.STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public void fromCSVSaveString(String bossString, ThingDatabase<Boss> database)
    {
        String[] bossList = bossString.split(CampaignConstants.STRING_SEPARATOR);

        for(String c: bossList)
        {
            this.add(database.lookup(Integer.parseInt(c)));
        }
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Boss c: getBosses())
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(CampaignConstants.STRING_SEPARATOR);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, ThingDatabase<Boss> database)
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
        for(Boss c: getBosses())
        {
            stringBuilder.append(c.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public BossList fromBossList(String bossList, ThingDatabase<Boss> database)
    {
        String[] bossesList = bossList.split("\n");
        BossList bosses = new BossList(new ArrayList<>());
        for(String c: bossesList)
        {
            bosses.add(database.lookup(c));
        }
        return bosses;
    }

    public int getBossIndex(Boss b) {
        return this.indexOf(b);
    }
}
