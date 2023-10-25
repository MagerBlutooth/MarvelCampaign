package adventure.model.target.base;

import adventure.model.sorter.AdvTokenSortMode;
import adventure.model.sorter.AdvTokenSorter;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.TargetList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AdvTokenList extends TargetList<AdvToken> {

    AdvTokenSorter tokenSorter = new AdvTokenSorter();

    public AdvTokenList(List<AdvToken> tokens)
    {
        super(tokens);
    }

    public AdvTokenList(AdvTokenList tokens)
    {
        super(tokens.getThings());
        tokenSorter = tokens.tokenSorter;
    }

    public void sort()
    {
        List<AdvToken> sortedTokens = tokenSorter.sort(new ArrayList<>(getTokens()));
        this.clear();
        this.addAll(sortedTokens);
    }
    public void setSortMode(String m) {
        tokenSorter.changeMode(AdvTokenSortMode.parseString(m));
    }

    public List<AdvToken> getTokens() {
        return getThings();
    }

    public String toCSVSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(AdvToken t: getTokens())
        {
            stringBuilder.append(t.getID());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(AdvToken t: getTokens())
        {
            stringBuilder.append(t.getID());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, TargetDatabase<AdvToken> database)
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

    public AdvTokenList cloneNewList(List<AdvToken> existingList) {
        AdvTokenList clonedLocs = new AdvTokenList(new ArrayList<>());
        for(AdvToken t: existingList)
        {
            clonedLocs.add(new AdvToken(t));
        }
        return clonedLocs;
    }
}
