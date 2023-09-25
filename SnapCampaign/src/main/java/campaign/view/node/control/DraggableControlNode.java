package campaign.view.node.control;

import campaign.controller.ControllerDatabase;
import campaign.controller.grid.DraggableThingDisplayController;
import campaign.model.thing.Card;
import campaign.model.thing.Location;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.dragdrop.Draggable;

import java.util.Objects;

public class DraggableControlNode<T extends Thing> extends ControlNode<T> implements Draggable<T> {


    DraggableThingDisplayController<T> dragController;

    public DraggableControlNode(DraggableThingDisplayController<T> controller)
    {
        dragController = controller;
    }

    @Override
    public void initialize(ControllerDatabase db, T t, IconImage i, ViewSize v, boolean blind) {

        super.initialize(db, t, i, v, blind);
    }

    @Override
    public void removeOnDrag(T t) {
        dragController.remove(t);
    }

    @Override
    public void toggle()
    {
        if (Objects.requireNonNull(getThingType()) == ThingType.CARD) {
            Card c = (Card) getSubject();
            c.setWounded(!c.isWounded());
        }
        else if(Objects.requireNonNull(getThingType()) == ThingType.LOCATION)
        {
            Location l = (Location) getSubject();
            l.setRuined(!l.isRuined());
        }
    }
}
