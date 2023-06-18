package model.constants;

public enum PlanningPurchase {
    INVESTIGATE("Investigate", 1, "Investigate an unknown Location"), INTERROGATE("Interrogate", 2, "Gain 1 Intel from a Captured Agent"), MINE("Mine", 2, "Obtain an Infinity Stone from an owned Location"),REPAIR("Repair", 3, "Repair a destroyed Location you own"), HIRE("Hire", 4, "Draft one of 3 Free Agents as a Merc"), BRAINWASH("Brainwash", 4, "Convert a Captured Agent to your Faction"), RECRUIT("Recruit", 8, "Add one of 3 Free Agents to your Squad"), SUPER("", 20, "");
    int cost;
    String prettyString;
    String description;
    PlanningPurchase(String n, int c, String d)
    {
        setPrettyString(n);
        setCost(c);
        setDescription(d);
    }

    private void setDescription(String d) {
        description = d;
    }

    private void setPrettyString(String n) {
        prettyString = n;
    }

    private void setCost(int c) {
        cost = c;
    }
    public int getCost()
    {
        return cost;
    }
    public String getPrettyString()
    {
        return prettyString;
    }

    public String getDescription() {
        return description;
    }
}
