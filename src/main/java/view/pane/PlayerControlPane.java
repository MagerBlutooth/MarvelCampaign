package view.pane;

import controller.ControllerDatabase;
import controller.PlayerControlPaneController;
import controller.WatcherControlPaneController;
import javafx.scene.layout.StackPane;
import model.database.CampaignDatabase;
import model.database.MasterThingDatabase;
import model.thing.Campaign;
import model.thing.Faction;
import view.fxml.FXMLGrabber;

public class PlayerControlPane extends CampaignPane {

    PlayerControlPaneController controller;

    public PlayerControlPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("playerControlPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(CampaignDatabase cD, ControllerDatabase db, Faction f, Faction e, Faction u)
    {
        controller.initialize(cD, db, f,e,u);
    }
}
