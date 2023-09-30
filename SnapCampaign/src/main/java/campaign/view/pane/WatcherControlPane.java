package campaign.view.pane;

import campaign.controller.MainDatabase;
import campaign.controller.WatcherControlPaneController;
import campaign.model.thing.Campaign;
import campaign.view.fxml.FXMLCampaignGrabber;

public class WatcherControlPane extends BasicPane {

    WatcherControlPaneController controller;

    public WatcherControlPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("watcherControlPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase db, Campaign campaign)
    {
        controller.initialize(db, campaign);
    }
}
