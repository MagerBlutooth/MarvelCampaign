package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.WatcherControlPaneController;
import snapMain.model.target.Campaign;
import snapMain.view.fxml.FXMLMainGrabber;

public class WatcherControlPane extends FullViewPane {

    WatcherControlPaneController controller;

    public WatcherControlPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("watcherControlPane.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase db, Campaign campaign)
    {
        controller.initialize(db, campaign);
    }
}
