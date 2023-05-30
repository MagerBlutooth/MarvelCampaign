package view.pane;

import controller.PlayerLoadPaneController;
import controller.ControllerDatabase;
import javafx.scene.layout.StackPane;
import view.fxml.FXMLGrabber;

public class PlayerLoadPane extends CampaignPane {
    PlayerLoadPaneController controller;

    public PlayerLoadPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("playerLoadPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
