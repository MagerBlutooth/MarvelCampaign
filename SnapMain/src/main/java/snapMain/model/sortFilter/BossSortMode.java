package snapMain.model.sortFilter;

public enum BossSortMode {
    NAME("Name");

    final String prettyString;

    BossSortMode(String pretty)
    {
        prettyString = pretty;
    }

    public String toString()
    {
        return prettyString;
    }

    public static BossSortMode parseString(String s)
    {
        for(BossSortMode c: values())
        {
            if(c.prettyString.equals(s))
                return c;
        }
        return null;
    }

}
