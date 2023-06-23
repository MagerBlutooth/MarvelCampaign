package view.pane;

import controller.ControllerDatabase;
import controller.WatcherControlPaneController;
import model.thing.Campaign;
import view.fxml.FXMLGrabber;

public class WatcherControlPane extends CampaignPane {

    WatcherControlPaneController controller;

    public WatcherControlPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("watcherControlPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase db, Campaign campaign)
    {
        controller.initialize(db, campaign);
    }
}
