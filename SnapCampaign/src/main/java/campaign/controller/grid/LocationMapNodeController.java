package campaign.controller.grid;

import campaign.controller.MainDatabase;
import campaign.controller.ScrollSetup;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import campaign.model.thing.Faction;
import campaign.model.thing.Location;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.DroppableLocationDisplayNode;
import campaign.view.node.LocationMapNode;
import campaign.view.node.control.ControlNode;
import campaign.view.node.control.DraggableMapControlNode;

import java.util.List;

public class LocationMapNodeController implements GridActionController<Location> {

    @FXML
    ScrollPane scrollPane;
    @FXML
    LocationMapNode locationMapNode;
    @FXML
    DroppableLocationDisplayNode locationGrid;
    @FXML
    Label agentCounter;

    MainDatabase mainDatabase;
    Faction faction;


    public void initialize(MainDatabase d, Faction f, boolean blind)
    {
        mainDatabase = d;
        faction = f;
        locationGrid.initialize(f, ThingType.LOCATION, this, ViewSize.MEDIUM, blind);
        ScrollSetup scrollSetup = new ScrollSetup();
        scrollSetup.setupScrolling(scrollPane);
    }

    @Override
    public ControlNode<Location> createControlNode(Location l, IconImage i, ViewSize v, boolean blind) {
        DraggableMapControlNode n = new DraggableMapControlNode(this);
        n.initialize(mainDatabase, l, i, v, blind);
        return n;
    }

    @Override
    public MainDatabase getDatabase() {
        return mainDatabase;
    }

    @Override
    public void saveGridNode(ControlNode<Location> node) {
    }

    @Override
    public void createTooltip(ControlNode<Location> n) {
    }

    @Override
    public void createContextMenu(ControlNode<Location> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        n.setOnContextMenuRequested(e -> rightClickMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<Location> displayControlNode) {

    }

    public List<Location> getLocations() {
        return locationGrid.getLocations();
    }

    public void refresh()
    {
        locationGrid.refreshToMatch(faction.getOwnedLocationsAndMedbay());
    }

    public void reset() {
        faction.clearStationedAgents();
        refresh();
    }

    public Faction getFaction() {
        return faction;
    }
}
