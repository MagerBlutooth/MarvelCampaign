package adventure.model.sorter;

public enum AdvTokenSortMode {
    NAME("Name");

    final String prettyString;

    AdvTokenSortMode(String pretty)
    {
        prettyString = pretty;
    }

    public String toString()
    {
        return prettyString;
    }

    public static AdvTokenSortMode parseString(String s)
    {
        for(AdvTokenSortMode c: values())
        {
            if(c.prettyString.equals(s))
                return c;
        }
        return null;
    }

}
