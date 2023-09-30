package campaign.view.pane;

import campaign.controller.BaseStartPaneController;
import campaign.controller.MainDatabase;
import campaign.view.fxml.FXMLCampaignGrabber;

public class CampaignStartPane extends BasicPane {

    BaseStartPaneController controller;

    public CampaignStartPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("campaignStartPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }

}
