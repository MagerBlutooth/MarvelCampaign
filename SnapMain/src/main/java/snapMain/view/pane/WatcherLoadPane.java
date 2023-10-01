package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.WatcherLoadPaneController;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class WatcherLoadPane extends FullViewPane {

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
