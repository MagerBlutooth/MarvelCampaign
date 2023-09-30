package campaign.view.pane;

import campaign.controller.MainDatabase;
import campaign.controller.WatcherLoadPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class WatcherLoadPane extends BasicPane {

    WatcherLoadPaneController controller;

    public WatcherLoadPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("watcherLoadPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }

}
