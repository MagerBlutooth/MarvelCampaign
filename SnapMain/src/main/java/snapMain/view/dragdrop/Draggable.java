package snapMain.view.dragdrop;

import snapMain.model.thing.BaseObject;
import snapMain.model.thing.TargetType;

public interface Draggable<T extends BaseObject> {
    void removeOnDrag(T t);
    TargetType getThingType();
}
