package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.PlayerControlPaneController;
import snapMain.model.database.CampaignDatabase;
import snapMain.model.target.Faction;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class PlayerControlPane extends FullViewPane {

    PlayerControlPaneController controller;

    public PlayerControlPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("playerControlPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(CampaignDatabase cD, MainDatabase db, Faction f, Faction e, Faction u)
    {
        controller.initialize(cD, db, f,e,u);
    }
}
