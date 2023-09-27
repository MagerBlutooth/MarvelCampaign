package adventure.view.pane;

import adventure.controller.manager.AdvLocationManagerPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.ControllerDatabase;
import campaign.controller.grid.LocationManagerPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.manager.ManagerPane;

public class AdvLocationManagerPane extends ManagerPane {

    AdvLocationManagerPaneController controller;
    public AdvLocationManagerPane()
    {

        FXMLAdventureGrabber grabber = new FXMLAdventureGrabber();
        grabber.grabFXML("sectionManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(AdvControllerDatabase database)
    {
        controller.initialize(database);
    }

}
