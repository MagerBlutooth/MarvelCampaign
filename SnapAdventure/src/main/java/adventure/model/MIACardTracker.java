package adventure.model;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import snapMain.model.constants.SnapMainConstants;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MIACardTracker {

    ConcurrentHashMap<Integer, ActiveCardList> miaCardMap;

    public MIACardTracker() {
        miaCardMap = new ConcurrentHashMap<>();
        for (int i = 1; i <= AdventureConstants.NUMBER_OF_WORLDS + 2; i++) {
            miaCardMap.put(i, new ActiveCardList());
        }
    }

    public void addCard(int world, ActiveCard a) {
        miaCardMap.get(world).add(a);
    }

    public String toSaveString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, ActiveCardList> e : miaCardMap.entrySet()) {
            stringBuilder.append(e.getKey());
            if(!e.getValue().isEmpty()) {
                stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
                for (ActiveCard c : e.getValue()) {
                    stringBuilder.append(c.getID());
                    stringBuilder.append(SnapMainConstants.SUBCATEGORY_SEPARATOR);
                }
            }
            stringBuilder.append(SnapMainConstants.CATEGORY_SEPARATOR);
        }
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, ActiveCardList cards) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if (decodedString.isBlank())
            return;
        String[] splitString = decodedString.split(SnapMainConstants.CATEGORY_SEPARATOR);
        for (String s : splitString) {
            String[] worldString = s.split(SnapMainConstants.STRING_SEPARATOR);
            if (worldString.length > 1) {
                int worldNum = Integer.parseInt(worldString[0]);
                String[] cardsList = worldString[1].split(SnapMainConstants.SUBCATEGORY_SEPARATOR);
                for (String cardId : cardsList) {
                    miaCardMap.get(worldNum).add(cards.getFromId(Integer.parseInt(cardId)));
                }
            }
        }
    }

    public ActiveCardList lookup(int i) {
        return miaCardMap.get(i);
    }
}
