package adventure.model.stats;

import snapMain.model.constants.SnapMainConstants;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WorldStatTracker {
    ConcurrentHashMap<AdvMatchResult, Integer> worldStatMap;

    public WorldStatTracker()
    {
        worldStatMap = new ConcurrentHashMap<>();
        for(AdvMatchResult m: AdvMatchResult.values())
        {
            worldStatMap.put(m, 0);
        }
    }

    public int getNumMatches()
    {
        int numMatches = 0;
        for(Map.Entry<AdvMatchResult, Integer> e: worldStatMap.entrySet())
        {
            numMatches += e.getValue();
        }
        return numMatches;
    }

    public void incrementNumMatches(AdvMatchResult r) {
        int i = worldStatMap.get(r);
        worldStatMap.replace(r, i+1);
    }

    public String toSaveString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<AdvMatchResult, Integer> e: worldStatMap.entrySet())
        {
            stringBuilder.append(e.getKey());
            stringBuilder.append(SnapMainConstants.CATEGORY_SEPARATOR);
            stringBuilder.append(e.getValue());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
        }
        if(!worldStatMap.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String saveString = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(saveString.getBytes());
    }

    public void fromSaveString(String saveString)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        worldStatMap = new ConcurrentHashMap<>();
        String[] mapSplit = decodedString.split(SnapMainConstants.STRING_SEPARATOR);
        for(String s: mapSplit)
        {
            String[] entryString = s.split(SnapMainConstants.CATEGORY_SEPARATOR);
            worldStatMap.put(AdvMatchResult.valueOf(entryString[0]), Integer.parseInt(entryString[1]));
        };
    }

    public int getNumMatchType(AdvMatchResult m) {
        return worldStatMap.get(m);
    }
}
