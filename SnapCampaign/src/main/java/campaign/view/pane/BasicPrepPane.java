package campaign.view.pane;

import campaign.controller.BasePrepPaneController;
import campaign.controller.MainDatabase;
import javafx.scene.Node;
import campaign.model.thing.Faction;
import campaign.view.fxml.FXMLCampaignGrabber;

public class BasicPrepPane extends BasicPane {

    BasePrepPaneController controller;

    //Class used to show a Player the current state of the campaign before advancing forward.
    //Only shows info about your faction, not the enemy's.
    public BasicPrepPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("campaignPrepPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase db, Faction shield, Faction hydra)
    {
        controller.initialize(db, shield, hydra);
    }

    public void addNode(Node n)
    {
        controller.addNode(n);
    }
}
