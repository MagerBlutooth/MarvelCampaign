package snapMain.view.pane.manager;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.LocationManagerPaneController;
import snapMain.view.fxml.FXMLMainGrabber;

public class LocationManagerPane extends ManagerPane {

    LocationManagerPaneController controller;
    public LocationManagerPane()
    {

        FXMLMainGrabber grabber = new FXMLMainGrabber();
        grabber.grabFXML("locationManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }

}
