package campaign.view.pane;

import campaign.controller.ControllerDatabase;
import campaign.controller.OptionsMenuPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class OptionsMenuPane extends CampaignPane {

    OptionsMenuPaneController controller;

    public OptionsMenuPane() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("optionsMenu.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
