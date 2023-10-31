package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.WatcherLoadPaneController;
import snapMain.view.fxml.FXMLMainGrabber;

public class WatcherLoadPane extends FullViewPane {

    WatcherLoadPaneController controller;

    public WatcherLoadPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("watcherLoadPane.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }

}
