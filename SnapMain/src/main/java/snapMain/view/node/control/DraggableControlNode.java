package snapMain.view.node.control;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.DraggableThingDisplayController;
import snapMain.model.target.Card;
import snapMain.model.target.Location;
import snapMain.model.target.BaseObject;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.dragdrop.Draggable;

import java.util.Objects;

public class DraggableControlNode<T extends BaseObject> extends ControlNode<T> implements Draggable<T> {


    DraggableThingDisplayController<T> dragController;

    public DraggableControlNode(DraggableThingDisplayController<T> controller)
    {
        dragController = controller;
    }

    @Override
    public void initialize(MainDatabase db, T t, IconImage i, ViewSize v, boolean blind) {

        super.initialize(db, t, i, v, blind);
    }

    @Override
    public void removeOnDrag(T t) {
        dragController.remove(t);
    }

    @Override
    public void toggle()
    {
        if (Objects.requireNonNull(getThingType()) == TargetType.CARD) {
            Card c = (Card) getSubject();
            //c.setWounded(!c.isWounded());
            //c.setCaptain(!c.isCaptain());
        }
        else if(Objects.requireNonNull(getThingType()) == TargetType.LOCATION)
        {
            Location l = (Location) getSubject();
            l.setRuined(!l.isRuined());
        }
    }
}
