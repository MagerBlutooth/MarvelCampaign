package campaign.view.pane;

import campaign.controller.MainDatabase;
import campaign.controller.PlayerMainMenuController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class PlayerMainMenuPane extends BasicPane {

    PlayerMainMenuController controller;
    public PlayerMainMenuPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("playerMainMenu.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
