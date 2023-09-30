package campaign.view.pane;

import campaign.controller.MainDatabase;
import campaign.controller.PlayerLoadPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class PlayerLoadPane extends BasicPane {
    PlayerLoadPaneController controller;

    public PlayerLoadPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("playerLoadPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }

}
