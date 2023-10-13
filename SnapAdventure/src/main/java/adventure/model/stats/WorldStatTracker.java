package adventure.model.stats;

public class WorldStatTracker {
    int numMatches;

    public void setNumMatches(int n)
    {
        numMatches = n;
    }

    public int getNumMatches()
    {
        return numMatches;
    }

    public void incrementNumMatches() {
        numMatches++;
    }

    public int toSaveString() {
        return numMatches;
    }

    public void fromSaveString(String s)
    {
        numMatches = Integer.parseInt(s);
    }
}
