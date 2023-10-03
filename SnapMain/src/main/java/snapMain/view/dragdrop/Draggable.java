package snapMain.view.dragdrop;

import snapMain.model.target.BaseObject;
import snapMain.model.target.TargetType;

public interface Draggable<T extends BaseObject> {
    void removeOnDrag(T t);
    TargetType getThingType();
}
