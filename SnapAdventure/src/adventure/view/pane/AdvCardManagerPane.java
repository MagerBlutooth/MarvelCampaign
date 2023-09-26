package adventure.view.pane;


import adventure.controller.manager.AdvCardManagerPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.ControllerDatabase;
import campaign.controller.grid.CardManagerPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.manager.ManagerPane;

public class AdvCardManagerPane extends ManagerPane {

    AdvCardManagerPaneController controller;
    public AdvCardManagerPane()
    {

        FXMLAdventureGrabber grabber = new FXMLAdventureGrabber();
        grabber.grabFXML("cardManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(AdvControllerDatabase database)
    {
        controller.initialize(database);
    }
}
