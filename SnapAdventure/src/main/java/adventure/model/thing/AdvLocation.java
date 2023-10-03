package adventure.model.thing;

import snapMain.model.target.*;

public class AdvLocation extends BaseObject {
    Location location;
    String effect;

    public AdvLocation(Location l)
    {
        location = l;
        setName(l.getName());
        setID(l.getID());
        effect = "";
    }

    public AdvLocation(AdvLocation advLocation) {
        location = advLocation.getLocation();
        effect = advLocation.getEffect();
        setID(advLocation.getID());
        setEnabled(advLocation.isEnabled());
    }

    @Override
    public String[] toSaveStringArray() {
        return new String[]{ location.getID()+"", location.getName(), isEnabled()+"", getEffect()};
    }

    @Override
    public void fromSaveStringArray(String[] mInfo) {
        setEnabled(Boolean.parseBoolean(mInfo[2]));
        effect = mInfo[3];
    }

    public String getEffect()
    {
        return effect;
    }

    @Override
    public boolean hasAttribute(String att) {
        return location.hasAttribute(att);
    }

    @Override
    public AdvLocation clone() {
       return new AdvLocation(this);
    }

    public Location getLocation() {
        return location;
    }

    public void setEffect(String text) {
        effect = text;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.LOCATION;
    }
}
