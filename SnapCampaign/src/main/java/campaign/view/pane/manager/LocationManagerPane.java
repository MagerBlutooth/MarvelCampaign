package campaign.view.pane.manager;

import campaign.controller.ControllerDatabase;
import campaign.controller.grid.LocationManagerPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class LocationManagerPane extends ManagerPane {

    LocationManagerPaneController controller;
    public LocationManagerPane()
    {

        FXMLCampaignGrabber grabber = new FXMLCampaignGrabber();
        grabber.grabFXML("locationManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}