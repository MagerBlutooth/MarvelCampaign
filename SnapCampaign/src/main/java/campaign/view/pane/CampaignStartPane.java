package campaign.view.pane;

import campaign.controller.CampaignStartPaneController;
import campaign.controller.ControllerDatabase;
import campaign.view.fxml.FXMLCampaignGrabber;

public class CampaignStartPane extends CampaignPane {

    CampaignStartPaneController controller;

    public CampaignStartPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("campaignStartPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
