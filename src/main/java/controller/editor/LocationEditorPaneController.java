package controller.editor;

import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.thing.Location;
import model.thing.ThingType;
import view.ViewSize;
import view.grabber.ThingImageGrabber;
import view.node.editor.LocationEditorNode;
import view.pane.manager.CardManagerPane;
import view.pane.manager.LocationManagerPane;
import view.pane.manager.TokenManagerPane;
import view.thing.LocationView;


public class LocationEditorPaneController extends EditorPaneController {

    @FXML
    LocationEditorNode locationEditorNode;

    @FXML
    LocationView imagePane;

    ThingImageGrabber imageGrabber = new ThingImageGrabber(ThingType.LOCATION);

    public void initialize(ControllerDatabase database, Location l)
    {
        super.initialize(database);
        locationSetup(database, l);
        imagePane.initialize(l, ViewSize.LARGE, true);
    }

    private void locationSetup(ControllerDatabase d, Location l) {
        locationEditorNode.initialize(d, l);
    }

    @FXML
    private void saveLocation()
    {
        Location l = locationEditorNode.generateLocation();
        controllerDatabase.addLocation(l, imagePane.getImage());
        imageGrabber.saveImage(imagePane.getImage(), l.getID());
        LocationManagerPane locationManagerPane = new LocationManagerPane();
        locationManagerPane.initialize(controllerDatabase);
        changeScene(locationManagerPane);
    }

    @Override
    public void initializeButtonToolBar() {
        LocationManagerPane locManagerPane = new LocationManagerPane();
        locManagerPane.initialize(controllerDatabase);
        buttonToolBar.initialize(locManagerPane);
    }
}
