package campaign.view.pane;

import campaign.controller.ControllerDatabase;
import campaign.controller.PlayerLoadPaneController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class PlayerLoadPane extends CampaignPane {
    PlayerLoadPaneController controller;

    public PlayerLoadPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("playerLoadPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
