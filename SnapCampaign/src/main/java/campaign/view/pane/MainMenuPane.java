package campaign.view.pane;

import campaign.controller.MainDatabase;
import campaign.controller.MainMenuController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class MainMenuPane extends BasicPane {

    MainMenuController controller;
    public MainMenuPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("mainMenu.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
