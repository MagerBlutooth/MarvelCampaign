package snapMain.view.pane;

import snapMain.controller.BaseStartPaneController;
import snapMain.controller.MainDatabase;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class CampaignStartPane extends FullViewPane {

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
