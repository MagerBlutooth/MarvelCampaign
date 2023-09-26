package adventure.view.pane;

import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.ControllerDatabase;
import campaign.controller.grid.LocationManagerPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.manager.ManagerPane;

public class AdvLocationManagerPane extends ManagerPane {

    LocationManagerPaneController controller;
    public AdvLocationManagerPane()
    {

        FXMLAdventureGrabber grabber = new FXMLAdventureGrabber();
        grabber.grabFXML("locationManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
