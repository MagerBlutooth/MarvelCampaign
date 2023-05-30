package controller.grid;

import controller.ControllerDatabase;
import controller.ScrollSetup;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import model.thing.*;
import view.IconImage;
import view.ViewSize;
import view.node.DroppableLocationDisplayNode;
import view.node.LocationMapNode;
import view.node.control.ControlNode;
import view.node.control.DraggableMapControlNode;

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

    ControllerDatabase controllerDatabase;
    Faction faction;


    public void initialize(ControllerDatabase d, Faction f, boolean blind)
    {
        controllerDatabase = d;
        faction = f;
        locationGrid.initialize(f, ThingType.LOCATION, this, ViewSize.MEDIUM, blind);
        ScrollSetup scrollSetup = new ScrollSetup();
        scrollSetup.setupScrolling(scrollPane);
        agentCounter.textProperty().bind(faction.getAgentStationedProperty().asString());
    }

    @Override
    public ControlNode<Location> createControlNode(Location l, IconImage i, ViewSize v, boolean blind) {
        DraggableMapControlNode n = new DraggableMapControlNode(this);
        n.initialize(controllerDatabase, l, i, v, blind);
        return n;
    }

    @Override
    public ControllerDatabase getDatabase() {
        return controllerDatabase;
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
        locationGrid.refresh(faction.getOwnedLocationsAndMedbay());
    }

    public void reset() {
        faction.clearStationedAgents();
        refresh();
    }

    public Faction getFaction() {
        return faction;
    }
}
