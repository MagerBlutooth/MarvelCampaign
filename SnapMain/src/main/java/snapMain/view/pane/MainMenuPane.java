package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.MainMenuController;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class MainMenuPane extends FullViewPane {

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
