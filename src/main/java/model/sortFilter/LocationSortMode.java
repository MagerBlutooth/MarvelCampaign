package model.sortFilter;

public enum LocationSortMode {
    MAP_POSITION("Map Position"), NAME("Name");

    String prettyString;

    LocationSortMode(String pretty)
    {
        prettyString = pretty;
    }

    public String toString()
    {
        return prettyString;
    }

    public static LocationSortMode parseString(String s)
    {
        for(LocationSortMode l: values())
        {
            if(l.prettyString.equals(s))
                return l;
        }
        return null;
    }

}
