package adventure.model.stats;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import snapMain.model.constants.SnapMainConstants;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CardStatTracker {
    ConcurrentHashMap<Integer,CardStats> cardStatMap;

    public CardStatTracker()
    {
        cardStatMap = new ConcurrentHashMap<>();
    }

    public void initialize(ActiveCardList cards) {
        for(ActiveCard card: cards)
        {
            cardStatMap.put(card.getID(), new CardStats());
        }
    }

    public void updateCardStats(ActiveCardList deck, MatchResult result)
    {
        for(ActiveCard c: deck)
        {
            int id = c.getID();
            CardStats cardStats = cardStatMap.get(id);
            cardStats.updateCardStat(result);
        }
    }

    public int lookupCardStat(ActiveCard c, MatchResult result)
    {
        return cardStatMap.get(c.getID()).lookupStat(result);
    }

    public String toSaveString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<Integer, CardStats> e: cardStatMap.entrySet())
        {
            stringBuilder.append(e.getKey());
            stringBuilder.append(SnapMainConstants.CATEGORY_SEPARATOR);
            stringBuilder.append(e.getValue().toSaveString());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        if(!cardStatMap.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String saveString = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(saveString.getBytes());
    }

    public void fromSaveString(String saveString) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        String[] splitString = decodedString.split(SnapMainConstants.STRING_SEPARATOR);
        cardStatMap = new ConcurrentHashMap<>();
        for(String s: splitString)
        {
            String[] entryString = s.split(SnapMainConstants.CATEGORY_SEPARATOR);
            CardStats cardStats = new CardStats();
            cardStats.fromSaveString(entryString[1]);
            cardStatMap.put(Integer.parseInt(entryString[0]), cardStats);

        }
    }
}
