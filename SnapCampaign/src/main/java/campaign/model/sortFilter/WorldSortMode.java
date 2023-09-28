package campaign.model.sortFilter;

public enum WorldSortMode {
    WORLD_NUM("Number");

    final String prettyString;

    WorldSortMode(String pretty)
    {
        prettyString = pretty;
    }

    public String toString()
    {
        return prettyString;
    }

    public static WorldSortMode parseString(String s)
    {
        for(WorldSortMode c: values())
        {
            if(c.prettyString.equals(s))
                return c;
        }
        return null;
    }

}
