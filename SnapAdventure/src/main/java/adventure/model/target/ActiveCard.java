package adventure.model.target;

import adventure.model.AdventureConstants;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.helper.StringHelper;
import snapMain.model.logger.MLogger;
import snapMain.model.target.*;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Class referring to an active card being used during an Adventure with status effects
public class ActiveCard implements Playable<Card> {
    Card card;
    ConcurrentHashMap<StatusEffect, Boolean> statusEffectMap;
    boolean temp;

    MLogger logger = new MLogger(ActiveCard.class);
    public ActiveCard()
    {
        statusEffectMap = new ConcurrentHashMap<>();
        card = new Card();
        initializeStatusEffectMap();
        temp = false;
    }
    public ActiveCard(Card c)
    {
        this();
        card = c;
        temp = false;
    }

    public ActiveCard(ActiveCard a)
    {
        card = a.card;
        statusEffectMap = a.statusEffectMap;
    }

    private void initializeStatusEffectMap() {
        for(StatusEffect s: StatusEffect.values())
        {
            statusEffectMap.put(s, false);
        }
    }

    public boolean hasStatus(StatusEffect s)
    {
        return statusEffectMap.get(s);
    }

    public void toggleStatus(StatusEffect s)
    {
        statusEffectMap.replace(s, !statusEffectMap.get(s));
        logger.info(StringHelper.camelCase(s.toString()) + " status for " + this + " set to "
                + hasStatus(s));
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(card.getID()).append(SnapMainConstants.CSV_SEPARATOR);
        for(Map.Entry<StatusEffect, Boolean> e: statusEffectMap.entrySet())
        {
            stringBuilder.append(e.getKey()).append(SnapMainConstants.SUBCATEGORY_SEPARATOR);
            stringBuilder.append(e.getValue()).append(SnapMainConstants.CATEGORY_SEPARATOR);
        }
        if(!statusEffectMap.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(SnapMainConstants.CSV_SEPARATOR);
        stringBuilder.append(temp);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, TargetDatabase<Card> cards)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] splitString = decodedString.split(SnapMainConstants.CSV_SEPARATOR);
        card = cards.lookup(Integer.parseInt(splitString[0]));
        String[] statusString = splitString[1].split(SnapMainConstants.CATEGORY_SEPARATOR);
        for(String s: statusString)
        {
            String[] effectString = s.split(SnapMainConstants.SUBCATEGORY_SEPARATOR);
            StatusEffect e = StatusEffect.valueOf(effectString[0]);
            boolean b = Boolean.parseBoolean(effectString[1]);
            statusEffectMap.replace(e, b);
        }
        temp = Boolean.parseBoolean(splitString[2]);
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.CARD;
    }

    @Override
    public int getID() {
        return card.getID();
    }

    @Override
    public void setEnabled(boolean e) {

    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return card.getName();
    }

    @Override
    public void setID(int id) {
        card.setID(id);
    }

    @Override
    public boolean hasAttribute(String entry) {
        if(entry.equals(AdventureConstants.TEMP_FILTER_STRING))
            return temp;
        return card.hasAttribute(entry);

    }

    public String toString()
    {
        return getName();
    }

    public Card getCard() {
        return card;
    }

    public int getCost() {
        return card.getCost();
    }

    public int getPower()
    {
        return card.getPower();
    }

    public int getPool()
    {
        return card.getPool();
    }

    @Override
    public String getEffect() {
        return "";
    }

    public void setStatus(StatusEffect s, boolean b) {
        statusEffectMap.replace(s, b);
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof ActiveCard))
            return false;
        return ((ActiveCard)o).getID() == getID();
    }

    public boolean hasAnyAttributes(List<CardAttribute> cardAttributes) {
        return getCard().hasAnyAttributes(cardAttributes);
    }

    public void setTemp(boolean t) {
        temp = t;
    }
}
