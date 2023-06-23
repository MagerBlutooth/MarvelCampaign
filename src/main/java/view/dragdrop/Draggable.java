package view.dragdrop;

import model.thing.Thing;
import model.thing.ThingType;

public interface Draggable<T extends Thing> {
    void removeOnDrag(T t);
    ThingType getThingType();
}
