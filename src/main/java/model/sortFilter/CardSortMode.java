package model.sortFilter;

public enum CardSortMode {
    NAME("Name"), POOL("Pool"), COST("Cost"), POWER("Power");

    String prettyString;

    CardSortMode(String pretty)
    {
        prettyString = pretty;
    }

    public String toString()
    {
        return prettyString;
    }

    public static CardSortMode parseString(String s)
    {
        for(CardSortMode c: values())
        {
            if(c.prettyString.equals(s))
                return c;
        }
        return null;
    }

}
