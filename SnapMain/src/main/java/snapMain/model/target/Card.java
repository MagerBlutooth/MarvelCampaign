package snapMain.model.target;

import snapMain.model.constants.SnapMainConstants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Card extends EffectBaseObject implements Unit {

    int pool;
    int power;
    int cost;
    Map<CardAttribute, Boolean> cardAttributes;

    public Card()
    {
        super();
        name = "Random Mook";
        pool = 1;
        power = 0;
        cost = 1;
        initializeCardAttributes();
    }
    public Card(Card c)
    {
        super(c);
        pool = c.getPool();
        power = c.getPower();
        cost = c.getCost();
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
    public String[] toCSVSaveStringArray() {
        StringBuilder attributeStrings = new StringBuilder();
        for(Map.Entry<CardAttribute, Boolean>entrySet : cardAttributes.entrySet())
        {
            attributeStrings.append(entrySet.getKey()).append(SnapMainConstants.STRING_SEPARATOR)
                    .append(entrySet.getValue());
            attributeStrings.append(SnapMainConstants.CATEGORY_SEPARATOR);
        }
        return new String[]{ getID()+"", getName(), getCost()+"", getPower()+"", getPool()+"", getEffect(),
                String.valueOf(isEnabled()), attributeStrings.toString()};
    }

    @Override
    public void fromCSVSaveStringArray(String[] mInfo) {
        id = Integer.parseInt(mInfo[0]);
        name = mInfo[1];
        cost = Integer.parseInt(mInfo[2]);
        power = Integer.parseInt(mInfo[3]);
        pool = Integer.parseInt(mInfo[4]);
        effect = mInfo[5];
        enabled = Boolean.parseBoolean(mInfo[6]);
        initializeCardAttributes();
        String[] attributeString = mInfo[7].split(SnapMainConstants.CATEGORY_SEPARATOR);
        for(String attributeEntry: attributeString)
        {
            String[] attribute = attributeEntry.split(SnapMainConstants.STRING_SEPARATOR);
            cardAttributes.put(CardAttribute.parseString(attribute[0]), Boolean.parseBoolean(attribute[1]));
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


    public boolean hasAllAttributes(List<CardAttribute> cardAttributes) {
        for(CardAttribute c: cardAttributes)
        {
            if(!this.hasAttribute(c.toString()))
               return false;
        }
        return true;
    }

    public boolean hasAnyAttributes(List<CardAttribute> cardAttributes) {
        for(CardAttribute c: cardAttributes)
        {
            if(this.hasAttribute(c.toString()))
                return true;
        }
        return false;
    }
}
