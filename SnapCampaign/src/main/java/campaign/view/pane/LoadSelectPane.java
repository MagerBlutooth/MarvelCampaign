package campaign.view.pane;

import campaign.controller.MainDatabase;
import campaign.controller.LoadSelectPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class LoadSelectPane extends BasicPane {

    LoadSelectPaneController controller;
    public LoadSelectPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("loadSelectPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
