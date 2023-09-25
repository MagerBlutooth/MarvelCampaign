package campaign.model.thing;

import campaign.model.constants.CampaignConstants;

public abstract class Thing implements Cloneable {

    String name;
    int id;
    boolean enabled;

    public Thing()
    {
        enabled = true;
        id = CampaignConstants.NO_ICON_ID;
    }

    public Thing(Thing t)
    {
        id = t.getID();
        name = t.getName();
        enabled = t.isEnabled();
    }

    public abstract String[] toSaveStringArray();

    public int getID()
    {
        return id;
    }

    public abstract void fromSaveStringArray(String[] mInfo);

    public void setID(int i)
    {
        id = i;
    }

    public abstract ThingType getThingType();
    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public void setEnabled(boolean e) {
        enabled = e;
    }

    public void setName(String n) {
        name = n;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Thing))
            return false;
        return id == ((Thing)o).getID();
    }

    public abstract boolean hasAttribute(String att);

    @Override
    public abstract Thing clone();
}
