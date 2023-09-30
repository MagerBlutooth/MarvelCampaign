package campaign.controller.grid;

import campaign.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import campaign.model.sortFilter.LocationSortMode;
import campaign.model.thing.Location;
import campaign.model.thing.LocationList;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import campaign.view.manager.LocationManager;
import campaign.view.node.control.ControlNode;
import campaign.view.pane.LocationEditorPane;

public class LocationManagerPaneController extends ManagerPaneController<Location, MainDatabase> {
    @FXML
    LocationManager locationManager;

    @Override
    public Scene getCurrentScene() {
        return locationManager.getScene();
    }

    @Override
    public void initialize(MainDatabase m) {
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
        mainDatabase.saveDatabase(node.getSubject().getThingType());
    }

    @Override
    public void editSubject(ControlNode<Location> node) {
        LocationEditorPane locationEditorPane = new LocationEditorPane();
        locationEditorPane.initialize(mainDatabase, node.getSubject());
        changeScene(locationEditorPane);
    }

    @FXML
    public void addNewEntry()
    {
        LocationEditorPane locationEditorPane = new LocationEditorPane();
        locationEditorPane.initialize(mainDatabase, new Location());
        changeScene(locationEditorPane);
    }
}
