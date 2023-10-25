package snapMain.model.target;

import snapMain.model.constants.SnapMainConstants;

public abstract class BaseObject implements Cloneable, SnapTarget {

    String name;
    int id;
    boolean enabled;

    public BaseObject()
    {
        enabled = true;
        id = SnapMainConstants.NO_ICON_ID;
    }

    public BaseObject(BaseObject t)
    {
        id = t.getID();
        name = t.getName();
        enabled = t.isEnabled();
    }

    public abstract String[] toCSVSaveStringArray();

    public int getID()
    {
        return id;
    }

    public abstract void fromCSVSaveStringArray(String[] mInfo);

    public void setID(int i)
    {
        id = i;
    }
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
        if(!(o instanceof BaseObject))
            return false;
        return id == ((BaseObject)o).getID();
    }

    public abstract boolean hasAttribute(String att);

    @Override
    public abstract BaseObject clone();
}
