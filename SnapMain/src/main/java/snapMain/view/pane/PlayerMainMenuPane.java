package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.PlayerMainMenuController;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class PlayerMainMenuPane extends FullViewPane {

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
