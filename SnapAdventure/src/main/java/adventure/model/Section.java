package adventure.model;

import campaign.model.thing.Location;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;

public class Section extends Thing {
    Location location;
    String effect;
    boolean revealed;

    public Section(Location l)
    {
        location = l;
        setName(l.getName());
        setID(l.getID());
        effect = "";
        revealed = false;
    }

    public Section(Section section) {
        location = section.getLocation();
        effect = section.getEffect();
        setID(section.getID());
        setEnabled(section.isEnabled());
        revealed = section.revealed;
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
    public ThingType getThingType() {
        return ThingType.LOCATION;
    }

    @Override
    public boolean hasAttribute(String att) {
        return location.hasAttribute(att);
    }

    @Override
    public Section clone() {
       return new Section(this);
    }

    public Location getLocation() {
        return location;
    }

    public void setEffect(String text) {
        effect = text;
    }

    public void setRevealed(boolean r)
    {
        revealed = r;
    }

    public boolean isRevealed()
    {
        return revealed;
    }

}
