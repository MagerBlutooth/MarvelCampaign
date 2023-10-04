package snapMain.view.node;

import snapMain.controller.grid.DraggableThingDisplayController;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.BaseObject;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.dragdrop.Draggable;

import java.util.List;

public class DraggableThingDisplayNode<T extends BaseObject> extends GridDisplayNode<T> implements Draggable<T> {

    DraggableThingDisplayController<T> controller;

    public DraggableThingDisplayNode() {
        getFXMLGrabber().grabFXML("draggableThingDisplayNode.fxml", this);
        controller = getFXMLGrabber().getController();
    }

    @Override
    public void initialize(TargetList<T> things, TargetType tType, GridActionController<T> actionController, ViewSize viewSize, boolean blind)
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

    public void refreshToMatch(TargetList<T> things) {
        controller.refresh(things);
    }

    public void filterBy(String text, boolean remove) {
        controller.filter(text, remove);
    }

    @Override
    public void sortBy(String c) {
        controller.sort(c);
    }

    public TargetType getThingType()
    {
        return controller.getThingType();
    }
}
