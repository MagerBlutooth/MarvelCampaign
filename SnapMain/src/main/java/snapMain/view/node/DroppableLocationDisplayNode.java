package snapMain.view.node;

import snapMain.controller.grid.DroppableLocationDisplayController;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.Faction;
import snapMain.model.target.Location;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
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
    public void refreshToMatch(TargetList<Location> refreshList)
    {
        controller.refresh(refreshList);
    }
}
