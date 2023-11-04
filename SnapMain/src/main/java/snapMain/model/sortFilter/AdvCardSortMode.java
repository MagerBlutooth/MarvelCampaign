package snapMain.model.sortFilter;

public enum AdvCardSortMode {
    NAME("Name"), POWER("Power"), COST("Cost"), POOL("Pool");

    final String prettyString;

    AdvCardSortMode(String pretty)
    {
        prettyString = pretty;
    }

    public String toString()
    {
        return prettyString;
    }

    public static AdvCardSortMode parseString(String s)
    {
        for(AdvCardSortMode c: values())
        {
            if(c.prettyString.equals(s))
                return c;
        }
        return null;
    }

}
