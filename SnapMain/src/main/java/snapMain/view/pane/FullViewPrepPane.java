package snapMain.view.pane;

import snapMain.controller.BasePrepPaneController;
import snapMain.controller.MainDatabase;
import javafx.scene.Node;
import snapMain.model.target.Faction;
import snapMain.view.fxml.FXMLMainGrabber;

public class FullViewPrepPane extends FullViewPane {

    BasePrepPaneController controller;

    //Class used to show a Player the current state of the campaign before advancing forward.
    //Only shows info about your faction, not the enemy's.
    public FullViewPrepPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("campaignPrepPane.fxml", this);
        controller = fxmlMainGrabber.getController();
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
