package adventure.model.target.base;

import adventure.model.sorter.AdvCardSorter;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.sortFilter.AdvCardSortMode;
import snapMain.model.target.TargetList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AdvCardList extends TargetList<AdvCard> {

    AdvCardSorter advCardSorter = new AdvCardSorter();

    public AdvCardList(List<AdvCard> advCards)
    {
        super(advCards);
    }

    public AdvCardList(AdvCardList bosses)
    {
        super(bosses.getBosses());
        advCardSorter = bosses.advCardSorter;
    }

    public void sort()
    {
        List<AdvCard> sortedAdvCards = advCardSorter.sort(new ArrayList<>(getBosses()));
        this.clear();
        this.addAll(sortedAdvCards);
    }
    public void setSortMode(String m) {
        advCardSorter.changeMode(AdvCardSortMode.parseString(m));
    }

    public List<AdvCard> getBosses() {
        return getThings();
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

}
