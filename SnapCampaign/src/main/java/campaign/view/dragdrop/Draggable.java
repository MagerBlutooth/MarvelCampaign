package campaign.view.dragdrop;

import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;

public interface Draggable<T extends Thing> {
    void removeOnDrag(T t);
    ThingType getThingType();
}
