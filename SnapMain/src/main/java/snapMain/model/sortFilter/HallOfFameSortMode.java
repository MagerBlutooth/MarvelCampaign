package snapMain.model.sortFilter;

public enum HallOfFameSortMode {
    NAME("Name"), ID("ID");

    final String prettyString;

    HallOfFameSortMode(String pretty)
    {
        prettyString = pretty;
    }

    public String toString()
    {
        return prettyString;
    }

    public static HallOfFameSortMode parseString(String s)
    {
        for(HallOfFameSortMode d: values())
        {
            if(d.prettyString.equals(s))
                return d;
        }
        return null;
    }

}
