package campaign.view.pane.manager;

import campaign.controller.ControllerDatabase;
import campaign.controller.grid.CardManagerPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class CardManagerPane extends ManagerPane{

    CardManagerPaneController controller;
    public CardManagerPane()
    {

        FXMLCampaignGrabber grabber = new FXMLCampaignGrabber();
        grabber.grabFXML("cardManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
