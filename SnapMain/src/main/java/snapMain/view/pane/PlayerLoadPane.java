package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.PlayerLoadPaneController;
import snapMain.view.fxml.FXMLMainGrabber;

public class PlayerLoadPane extends FullViewPane {
    PlayerLoadPaneController controller;

    public PlayerLoadPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("playerLoadPane.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }

}
