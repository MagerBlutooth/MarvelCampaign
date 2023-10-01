package snapMain.model.thing;

import snapMain.model.constants.CampaignConstants;

import java.util.LinkedHashMap;
import java.util.Map;

public class Card extends EffectBaseObject implements Playable {

    int pool;
    int power;
    int cost;
    Map<CardAttribute, Boolean> cardAttributes;
    boolean captain;
    boolean wounded;

    public Card()
    {
        super();
        name = "New Card";
        pool = 1;
        power = 0;
        cost = 1;
        captain = false;
        wounded = false;
        initializeCardAttributes();
    }

    public Card(Card c)
    {
        super(c);
        pool = c.getPool();
        power = c.getPower();
        cost = c.getCost();
        captain = c.isCaptain();
        wounded = c.isWounded();
        cardAttributes = c.getCardAttributes();
    }

    private Map<CardAttribute, Boolean> getCardAttributes() {
        return cardAttributes;
    }

    private void initializeCardAttributes() {
        cardAttributes = new LinkedHashMap<>();
        for(CardAttribute cardAttribute: CardAttribute.values())
        {
            cardAttributes.put(cardAttribute, false);
        }
    }

    @Override
    public String[] toSaveStringArray() {
        StringBuilder attributeStrings = new StringBuilder();
        for(Map.Entry<CardAttribute, Boolean>entrySet : cardAttributes.entrySet())
        {
            attributeStrings.append(entrySet.getKey()).append(CampaignConstants.STRING_SEPARATOR).append(entrySet.getValue());
            attributeStrings.append(CampaignConstants.CATEGORY_SEPARATOR);
        }
        attributeStrings.substring(0,attributeStrings.length()); //Remove final separator
        return new String[]{ getID()+"", getName(), getCost()+"", getPower()+"", getPool()+"", getEffect(), String.valueOf(isEnabled()), attributeStrings.toString(), String.valueOf(isWounded()), String.valueOf(isCaptain())};
    }

    @Override
    public void fromSaveStringArray(String[] mInfo) {
        id = Integer.parseInt(mInfo[0]);
        name = mInfo[1];
        cost = Integer.parseInt(mInfo[2]);
        power = Integer.parseInt(mInfo[3]);
        pool = Integer.parseInt(mInfo[4]);
        effect = mInfo[5];
        enabled = Boolean.parseBoolean(mInfo[6]);
        initializeCardAttributes();
        String[] attributeString = mInfo[7].split(CampaignConstants.CATEGORY_SEPARATOR);
        for(String attributeEntry: attributeString)
        {
            String[] attribute = attributeEntry.split(CampaignConstants.STRING_SEPARATOR);
            cardAttributes.put(CardAttribute.parseString(attribute[0]), Boolean.parseBoolean(attribute[1]));
        }
        if(mInfo.length > 8) {
            wounded = Boolean.parseBoolean(mInfo[8]);
            captain = Boolean.parseBoolean(mInfo[9]);
        }
        else
        {
            wounded = false;
            captain = false;
        }
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.CARD;
    }

    public void setPower(int p)
    {
        power = p;
    }

    public void setCost(int c)
    {
        cost = c;
    }

    public void setPool(int p) {
        pool = p;
    }

    public int getPower() {
        return power;
    }

    public int getCost() {
        return cost;
    }

    public int getPool() {
        return pool;
    }
    public boolean isCaptain()
    {
        return captain;
    }

    public void setCaptain(boolean c)
    {
        captain = c;
    }

    public String toString()
    {
        return name;
    }

    public void setAttribute(CardAttribute c, boolean b) {
        cardAttributes.replace(c, b);
    }

    public boolean hasAttribute(String s) {
        CardAttribute cA = CardAttribute.parseString(s);
        return cardAttributes.get(cA);
    }

    @Override
    public BaseObject clone() {
        return new Card(this);
    }

    public boolean isWounded() {
        return wounded;
    }

    public void setWounded(boolean w) {
        wounded = w;
    }
}
