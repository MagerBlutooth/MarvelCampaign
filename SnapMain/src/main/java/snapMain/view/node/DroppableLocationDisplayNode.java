package snapMain.view.node;

import snapMain.controller.grid.DroppableLocationDisplayController;
import snapMain.controller.grid.GridActionController;
import snapMain.model.thing.Faction;
import snapMain.model.thing.Location;
import snapMain.model.thing.ThingList;
import snapMain.model.thing.TargetType;
import snapMain.view.ViewSize;

import java.util.List;
import java.util.Set;

public class DroppableLocationDisplayNode extends GridDisplayNode<Location> {

    DroppableLocationDisplayController controller;
    public DroppableLocationDisplayNode() {
        getFXMLGrabber().grabFXML("droppableLocationDisplayNode.fxml", this);
        controller = getFXMLGrabber().getController();
    }

    public void initialize(Faction f, TargetType t, GridActionController<Location> c, ViewSize v, boolean blind)
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
    public void refreshToMatch(ThingList<Location> refreshList)
    {
        controller.refresh(refreshList);
    }
}
