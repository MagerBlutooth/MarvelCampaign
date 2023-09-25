package campaign.view.node;

import campaign.controller.grid.DroppableLocationDisplayController;
import campaign.controller.grid.GridActionController;
import campaign.model.thing.Faction;
import campaign.model.thing.Location;
import campaign.model.thing.ThingList;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;

import java.util.List;
import java.util.Set;

public class DroppableLocationDisplayNode extends GridDisplayNode<Location> {

    DroppableLocationDisplayController controller;
    public DroppableLocationDisplayNode() {
        getFXMLGrabber().grabFXML("droppableLocationDisplayNode.fxml", this);
        controller = getFXMLGrabber().getController();
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
    public void refreshToMatch(ThingList<Location> refreshList)
    {
        controller.refresh(refreshList);
    }
}
