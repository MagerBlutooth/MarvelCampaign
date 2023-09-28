package adventure.model.sorter;

public enum SectionSortMode {
    NAME("Name");

    final String prettyString;

    SectionSortMode(String pretty)
    {
        prettyString = pretty;
    }

    public String toString()
    {
        return prettyString;
    }

    public static SectionSortMode parseString(String s)
    {
        for(SectionSortMode c: values())
        {
            if(c.prettyString.equals(s))
                return c;
        }
        return null;
    }

}
