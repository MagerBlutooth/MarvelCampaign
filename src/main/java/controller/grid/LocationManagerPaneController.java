package controller.grid;

import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import model.sortFilter.LocationSortMode;
import model.thing.Location;
import model.thing.LocationList;
import model.thing.ThingType;
import view.ViewSize;
import view.manager.LocationManager;
import view.node.control.ControlNode;
import view.pane.LocationEditorPane;

public class LocationManagerPaneController extends ManagerPaneController<Location> {
    @FXML
    LocationManager locationManager;

    @Override
    public Scene getCurrentScene() {
        return locationManager.getScene();
    }

    @Override
    public void initialize(ControllerDatabase m) {
        super.initialize(m);
        LocationList locations = new LocationList(m.getLocations());
        locations.setSortMode(LocationSortMode.NAME);
        locations.sort();
        locationManager.initialize(locations, ThingType.LOCATION, this, ViewSize.MEDIUM, true);
        locationManager.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
    }

    //Left click toggles the node. Right-click allows you to edit.
    @Override
    public void saveGridNode(ControlNode<Location> node) {
        controllerDatabase.saveDatabase(node.getSubject().getThingType());
    }

    @Override
    public void editSubject(ControlNode<Location> node) {
        LocationEditorPane locationEditorPane = new LocationEditorPane();
        locationEditorPane.initialize(controllerDatabase, node.getSubject());
        changeScene(locationEditorPane);
    }

    @FXML
    public void addNewEntry()
    {
        LocationEditorPane locationEditorPane = new LocationEditorPane();
        locationEditorPane.initialize(controllerDatabase, new Location());
        changeScene(locationEditorPane);
    }
}
