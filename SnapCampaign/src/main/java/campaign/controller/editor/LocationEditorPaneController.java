package campaign.controller.editor;

import campaign.controller.ControllerDatabase;
import javafx.fxml.FXML;
import campaign.model.thing.Location;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import campaign.view.grabber.ThingImageGrabber;
import campaign.view.node.editor.LocationEditorNode;
import campaign.view.pane.manager.LocationManagerPane;
import campaign.view.thing.LocationView;


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
