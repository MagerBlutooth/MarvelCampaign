package campaign.view.node;

import campaign.controller.grid.DraggableThingDisplayController;
import campaign.controller.grid.GridActionController;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingList;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import campaign.view.dragdrop.Draggable;

import java.util.List;

public class DraggableThingDisplayNode<T extends Thing> extends GridDisplayNode<T> implements Draggable<T> {

    DraggableThingDisplayController<T> controller;

    public DraggableThingDisplayNode() {
        getFXMLGrabber().grabFXML("draggableThingDisplayNode.fxml", this);
        controller = getFXMLGrabber().getController();
    }

    @Override
    public void initialize(ThingList<T> things, ThingType tType, GridActionController<T> actionController, ViewSize viewSize, boolean blind)
    {
        controller.initialize(things, tType, actionController, viewSize, blind);
    }

    public String toString()
    {
        return controller.toString();
    }

    @Override
    public void removeOnDrag(T t) {
        controller.remove(t);
    }

    public List<T> getThings()
    {
        return controller.getThings();
    }

    public void add(T t) {
        controller.add(t);
    }

    public void update(T t) {
        controller.update(t);
    }

    public DraggableThingDisplayController<T> getController()
    {
        return controller;
    }

    public void refreshToMatch(ThingList<T> things) {
        controller.refresh(things);
    }

    public void filterBy(String text, boolean remove) {
        controller.filter(text, remove);
    }

    @Override
    public void sortBy(String c) {
        controller.sort(c);
    }

    public ThingType getThingType()
    {
        return controller.getThingType();
    }
}
