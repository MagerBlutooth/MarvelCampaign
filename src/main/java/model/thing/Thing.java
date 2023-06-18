package model.thing;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.constants.CampaignConstants;

public abstract class Thing {

    String name;
    int id;
    boolean enabled;

    public Thing()
    {
        enabled = true;
        id = CampaignConstants.NO_ICON_ID;
    }

    public abstract String[] toSaveStringArray();

    public int getID()
    {
        return id;
    }

    abstract void fromSaveStringArray(String[] vInfo);

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

}
