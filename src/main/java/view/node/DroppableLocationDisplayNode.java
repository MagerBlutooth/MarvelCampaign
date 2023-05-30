package view.node;

import controller.grid.DroppableLocationDisplayController;
import controller.grid.GridActionController;
import model.thing.*;
import view.ViewSize;

import java.util.List;
import java.util.Set;

public class DroppableLocationDisplayNode extends GridDisplayNode<Location> {

    DroppableLocationDisplayController controller;
    public DroppableLocationDisplayNode() {
        fxmlGrabber.grabFXML("droppableLocationDisplayNode.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(Faction f, ThingType t, GridActionController<Location> c, ViewSize v, boolean blind)
    {
        controller.initialize(f, t, c, v, blind);
    }

    public String toString()
    {
        return controller.toString();
    }

    public void sortBy(String c) {
        controller.sort(c);
    }

    public List<String> getSortOptions() {
        return controller.getSortOptions();
    }

    public Set<String> getFilterOptions() {
        return controller.getFilterOptionKeys();
    }

    public void filterBy(String text, boolean b) {
        controller.filter(text, b);
    }

    public List<Location> getLocations() {
        return controller.getLocations();
    }

    @Override
    public void refresh(ThingList<Location> refreshList)
    {
        controller.refresh(refreshList);
    }
}
