package snapMain.controller.editor;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import snapMain.model.thing.Location;
import snapMain.model.thing.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.grabber.ThingImageGrabber;
import snapMain.view.node.editor.LocationEditorNode;
import snapMain.view.pane.manager.LocationManagerPane;
import snapMain.view.thing.LocationView;


public class LocationEditorPaneController extends EditorPaneController {

    @FXML
    LocationEditorNode locationEditorNode;

    @FXML
    LocationView imagePane;

    ThingImageGrabber imageGrabber = new ThingImageGrabber(TargetType.LOCATION);

    public void initialize(MainDatabase database, Location l)
    {
        super.initialize(database);
        locationSetup(database, l);
        imagePane.initialize(l, ViewSize.LARGE, true);
    }

    private void locationSetup(MainDatabase d, Location l) {
        locationEditorNode.initialize(d, l);
    }

    @FXML
    private void saveLocation()
    {
        Location l = locationEditorNode.generateLocation();
        mainDatabase.addLocation(l, imagePane.getImage());
        imageGrabber.saveImage(imagePane.getImage(), l.getID());
        LocationManagerPane locationManagerPane = new LocationManagerPane();
        locationManagerPane.initialize(mainDatabase);
        changeScene(locationManagerPane);
    }

    @Override
    public void initializeButtonToolBar() {
        LocationManagerPane locManagerPane = new LocationManagerPane();
        locManagerPane.initialize(mainDatabase);
        buttonToolBar.initialize(locManagerPane);
    }
}
