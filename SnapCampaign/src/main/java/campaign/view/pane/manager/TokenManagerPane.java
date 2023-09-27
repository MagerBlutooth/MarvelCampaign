package campaign.view.pane.manager;

import campaign.controller.ControllerDatabase;
import campaign.controller.grid.TokenManagerPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class TokenManagerPane extends ManagerPane {

    TokenManagerPaneController controller;
    public TokenManagerPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("tokenManagerPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }
}