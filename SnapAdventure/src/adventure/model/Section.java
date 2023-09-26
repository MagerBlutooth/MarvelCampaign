package adventure.model;

import campaign.model.thing.Location;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;

public class Section extends Thing {
    Location location;
    String effect;

    public Section(Location l)
    {
        location = l;
        effect = "";
    }

    public Section(Section section) {
        location = section.location;
        effect = section.effect;
        setID(section.getID());
        setEnabled(section.isEnabled());
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
}
