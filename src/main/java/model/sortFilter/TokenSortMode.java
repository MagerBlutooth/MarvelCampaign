package model.sortFilter;

public enum TokenSortMode {
    NAME("Name");

    String prettyString;

    TokenSortMode(String pretty)
    {
        prettyString = pretty;
    }

    public String toString()
    {
        return prettyString;
    }

    public static TokenSortMode parseString(String s)
    {
        for(TokenSortMode t: values())
        {
            if(t.prettyString.equals(s))
                return t;
        }
        return null;
    }

}
