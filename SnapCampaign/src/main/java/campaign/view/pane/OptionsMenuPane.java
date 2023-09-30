package campaign.view.pane;

import campaign.controller.MainDatabase;
import campaign.controller.OptionsMenuPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class OptionsMenuPane extends BasicPane {

    OptionsMenuPaneController controller;

    public OptionsMenuPane() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("optionsMenu.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
