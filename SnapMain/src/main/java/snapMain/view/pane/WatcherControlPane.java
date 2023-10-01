package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.WatcherControlPaneController;
import snapMain.model.thing.Campaign;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class WatcherControlPane extends FullViewPane {

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
