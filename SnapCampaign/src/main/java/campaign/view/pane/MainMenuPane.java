package campaign.view.pane;

import campaign.controller.ControllerDatabase;
import campaign.controller.MainMenuController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class MainMenuPane extends CampaignPane {

    MainMenuController controller;
    public MainMenuPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("mainMenu.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
