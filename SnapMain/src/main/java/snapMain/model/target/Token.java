package snapMain.model.target;

public class Token extends BaseObject implements SnapTarget {

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
    public String[] toCSVSaveStringArray() {
        return new String[]{String.valueOf(getID()), getName(), String.valueOf(getCost()), String.valueOf(getPower()), String.valueOf(isEnabled())};
    }

    @Override
    public void fromCSVSaveStringArray(String[] tInfo) {
        id = Integer.parseInt(tInfo[0]);
        name = tInfo[1];
        cost = Integer.parseInt(tInfo[2]);
        power = Integer.parseInt(tInfo[3]);
        enabled = Boolean.parseBoolean(tInfo[4]);
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

    @Override
    public TargetType getTargetType() {
        return TargetType.TOKEN;
    }

    public String toString()
    {
        return name;
    }

}
