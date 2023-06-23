package view.pane;

import controller.CampaignPrepPaneController;
import controller.ControllerDatabase;
import javafx.scene.Node;
import model.thing.Faction;
import view.fxml.FXMLGrabber;

public class CampaignPrepPane extends CampaignPane {

    CampaignPrepPaneController controller;

    //Class used to show a Player the current state of the campaign before advancing forward.
    //Only shows info about your faction, not the enemy's.
    public CampaignPrepPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("campaignPrepPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase db, Faction shield, Faction hydra)
    {
        controller.initialize(db, shield, hydra);
    }

    public void addNode(Node n)
    {
        controller.addNode(n);
    }
}
