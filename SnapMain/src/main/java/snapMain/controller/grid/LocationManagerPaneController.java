package snapMain.controller.grid;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import snapMain.model.sortFilter.LocationSortMode;
import snapMain.model.target.Location;
import snapMain.model.target.LocationList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.manager.LocationManager;
import snapMain.view.node.control.ControlNode;
import snapMain.view.pane.LocationEditorPane;

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
        locationManager.initialize(locations, TargetType.LOCATION, this, ViewSize.MEDIUM, true);
        locationManager.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
    }

    @Override
    public ControlNode<Location> createEmptyNode(ViewSize v) {
        ControlNode<Location> locationNode = new ControlNode<>();
        locationNode.initialize(mainDatabase, new Location(), mainDatabase.grabBlankImage(TargetType.LOCATION),
                v,false);
        return locationNode;
    }

    //Left click toggles the node. Right-click allows you to edit.
    @Override
    public void saveGridNode(ControlNode<Location> node) {
        mainDatabase.saveDatabase(node.getSubject().getTargetType());
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
