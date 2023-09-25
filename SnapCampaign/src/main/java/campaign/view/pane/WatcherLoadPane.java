package campaign.view.pane;

import campaign.controller.ControllerDatabase;
import campaign.controller.WatcherLoadPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class WatcherLoadPane extends CampaignPane {

    WatcherLoadPaneController controller;

    public WatcherLoadPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("watcherLoadPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
