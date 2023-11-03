package adventure.model.target.base;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.BaseObject;
import snapMain.model.target.Location;
import snapMain.model.target.TargetType;

public class AdvLocation extends BaseObject {
    Location location;
    String effect;

    public AdvLocation()
    {
        location = new Location();
        setID(SnapMainConstants.RUINS_ICON_ID);
    }

    public AdvLocation(Location l)
    {
        location = l;
        setName(l.getName());
        setID(l.getID());
        effect = "No Location Effect.";
    }

    public AdvLocation(AdvLocation advLocation) {
        location = advLocation.getLocation();
        effect = advLocation.getEffect();
        setID(advLocation.getID());
        setEnabled(advLocation.isEnabled());
    }
    @Override
    public void setID(int i)
    {
        location.setID(i);
        super.setID(i);
    }

    @Override
    public String[] toCSVSaveStringArray() {
        return new String[]{ location.getID()+"", location.getName(), isEnabled()+"", getEffect()};
    }

    @Override
    public void fromCSVSaveStringArray(String[] mInfo) {
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
