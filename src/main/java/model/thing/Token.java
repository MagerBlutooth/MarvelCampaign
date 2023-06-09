package model.thing;

public class Token extends Thing {

    int power;
    int cost;

    public Token()
    {
        super();
        name = "New Token";
    }
    public Token(Token t)
    {
        super(t);
        name = t.getName();
    }
    @Override
    public String[] toSaveStringArray() {
        return new String[]{String.valueOf(getID()), getName(), String.valueOf(getCost()), String.valueOf(getPower()), String.valueOf(isEnabled())};
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

    @Override
    public Token clone() {
        return new Token(this);
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
