package model.thing;

import java.util.Comparator;
import java.util.Map;

import static model.constants.CampaignConstants.CATEGORY_SEPARATOR;
import static model.constants.CampaignConstants.STRING_SEPARATOR;

public class Token extends Thing {

    int power;
    int cost;

    public Token()
    {
        super();
        name = "New Token";
    }
    @Override
    public String[] toSaveStringArray() {
        String[] stringArray = { getID()+"", getName(), getCost()+"", getPower()+"", String.valueOf(isEnabled())};
        return stringArray;
    }

    @Override
    public void fromSaveStringArray(String[] tInfo) {
        id = Integer.parseInt(tInfo[0]);
        name = tInfo[1];
        cost = Integer.parseInt(tInfo[2]);
        power = Integer.parseInt(tInfo[3]);
        enabled = Boolean.parseBoolean(tInfo[4]);
    }

    @Override
    public ThingType getThingType() {
        return ThingType.TOKEN;
    }

    @Override
    public boolean hasAttribute(String att) {
        return false;
    }

    public void setPower(int p)
    {
        power = p;
    }

    public void setCost(int c) {
        cost = c;
    };

    public int getCost() {
        return cost;
    }

    public int getPower()
    {
        return power;
    }
}
