package snapMain.view.pane;

import snapMain.controller.BasePrepPaneController;
import snapMain.controller.MainDatabase;
import javafx.scene.Node;
import snapMain.model.thing.Faction;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class FullViewPrepPane extends FullViewPane {

    BasePrepPaneController controller;

    //Class used to show a Player the current state of the campaign before advancing forward.
    //Only shows info about your faction, not the enemy's.
    public FullViewPrepPane()
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
