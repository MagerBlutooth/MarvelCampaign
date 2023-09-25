package campaign.view.pane;

import campaign.controller.ControllerDatabase;
import campaign.controller.LoadSelectPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class LoadSelectPane extends CampaignPane {

    LoadSelectPaneController controller;
    public LoadSelectPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("loadSelectPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
