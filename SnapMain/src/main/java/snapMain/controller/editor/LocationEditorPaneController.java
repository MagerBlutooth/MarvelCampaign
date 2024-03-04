package snapMain.controller.editor;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import snapMain.model.target.Location;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.grabber.TargetImageGrabber;
import snapMain.view.node.editor.LocationEditorNode;
import snapMain.view.pane.manager.LocationManagerPane;
import snapMain.view.thing.LocationView;


public class LocationEditorPaneController extends EditorPaneController {

    @FXML
    LocationView imagePane;
    @FXML
    LocationEditorNode locationEditorNode;
    TargetImageGrabber imageGrabber;

    public LocationEditorPaneController()
    {
        imageGrabber = new TargetImageGrabber(TargetType.LOCATION);
    }


    public void initialize(MainDatabase database, Location loc)
    {
        super.initialize(database);
        locationEditorNode.initialize(database, loc);
        imagePane.initialize(loc, ViewSize.LARGE, true);
    }

    @Override
    public void initializeButtonToolBar() {
        LocationManagerPane locManagerPane = new LocationManagerPane();
        locManagerPane.initialize(mainDatabase);
        buttonToolBar.initialize(locManagerPane);
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
}
