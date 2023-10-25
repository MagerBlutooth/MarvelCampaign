package adventure.model.target;

import adventure.model.sorter.ActiveCardFilter;
import adventure.model.sorter.ActiveCardSorter;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.sortFilter.CardSortMode;
import snapMain.model.target.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class InfinityStoneList extends TargetList<InfinityStone> {

    public InfinityStoneList()
    {
        super();
    }

    @Override
    public void sort() {

    }

    @Override
    public void setSortMode(String m) {

    }

    public InfinityStoneList(List<InfinityStone> infinity)
    {
        super(infinity);
    }

    public InfinityStoneList(InfinityStoneList stones)
    {
        super(stones.getThings());
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(InfinityStone i: getThings())
        {
            stringBuilder.append(i.toSaveString());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        if(!stringBuilder.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        else
            stringBuilder.append(" ");
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, TargetDatabase<Token> tokens)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        String[] cardsList = decodedString.split(SnapMainConstants.STRING_SEPARATOR);
        if(decodedString.isBlank())
            return;
        for(String c: cardsList)
        {
            InfinityStone i = new InfinityStone();
            i.fromSaveString(c, tokens);
            this.add(i);
        }
    }

    @Override
    public void clear()
    {
        getThings().clear();
    }

    public InfinityStoneList cloneNewList(List<InfinityStone> existingList) {
        InfinityStoneList cloned = new InfinityStoneList();
        for(InfinityStone i: existingList)
        {
            cloned.add(new InfinityStone(i));
        }
        return cloned;
    }
}
