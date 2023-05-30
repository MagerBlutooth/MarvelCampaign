package view.pane;

import controller.ControllerDatabase;
import controller.PlayerLoadPaneController;
import controller.WatcherLoadPaneController;
import javafx.scene.layout.StackPane;
import view.fxml.FXMLGrabber;

public class WatcherLoadPane extends CampaignPane {

    WatcherLoadPaneController controller;

    public WatcherLoadPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("watcherLoadPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
