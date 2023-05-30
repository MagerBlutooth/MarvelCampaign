package view.pane.manager;

import controller.ControllerDatabase;
import controller.grid.LocationManagerPaneController;
import view.fxml.FXMLGrabber;

public class LocationManagerPane extends ManagerPane {

    LocationManagerPaneController controller;
    public LocationManagerPane()
    {

        FXMLGrabber grabber = new FXMLGrabber();
        grabber.grabFXML("locationManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
