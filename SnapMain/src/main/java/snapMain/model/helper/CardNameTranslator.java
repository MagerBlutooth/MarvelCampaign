package snapMain.model.helper;

import java.util.concurrent.ConcurrentHashMap;

public class CardNameTranslator {

    ConcurrentHashMap<String, String> nameTranslationMap;

    public CardNameTranslator()
    {
        initializeMap();
    }

    private void initializeMap() {
        nameTranslationMap = new ConcurrentHashMap<>();
        nameTranslationMap.put("Mister Fantastic", "MrFantastic");
        nameTranslationMap.put("Mister Sinister", "MrSinister");
        nameTranslationMap.put("Mister Negative", "MrNegative");
        nameTranslationMap.put("Uatu the Watcher", "Uatu");
        nameTranslationMap.put("Doctor Doom", "DrDoom");
        nameTranslationMap.put("Jane Foster Mighty Thor", "JaneFoster");
        nameTranslationMap.put("The Infinaut", "Infinaut");
        nameTranslationMap.put("The Hood", "Hood");
        nameTranslationMap.put("The Living Tribunal", "Living Tribunal");
        nameTranslationMap.put("Phoenix Force", "The Phoenix Force");
    }

    public String translateName(String name)
    {
        String translatedName = nameTranslationMap.get(name);
        if(translatedName != null)
            return StringHelper.toDisplayFormat(translatedName);
        return StringHelper.toDisplayFormat(name);
    }
}
