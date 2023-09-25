package campaign.view.pane;

import campaign.controller.ControllerDatabase;
import campaign.controller.PlayerControlPaneController;
import campaign.model.database.CampaignDatabase;
import campaign.model.thing.Faction;
import campaign.view.fxml.FXMLCampaignGrabber;

public class PlayerControlPane extends CampaignPane {

    PlayerControlPaneController controller;

    public PlayerControlPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("playerControlPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(CampaignDatabase cD, ControllerDatabase db, Faction f, Faction e, Faction u)
    {
        controller.initialize(cD, db, f,e,u);
    }
}
