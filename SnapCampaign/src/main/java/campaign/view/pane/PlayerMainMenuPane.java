package campaign.view.pane;

import campaign.controller.ControllerDatabase;
import campaign.controller.PlayerMainMenuController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class PlayerMainMenuPane extends CampaignPane {

    PlayerMainMenuController controller;
    public PlayerMainMenuPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("playerMainMenu.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
