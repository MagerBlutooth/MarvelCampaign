package adventure.view.pane;

import adventure.controller.manager.AdvLocationManagerPaneController;
import adventure.controller.manager.AdvTokenManagerPaneController;
import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.manager.ManagerPane;

public class AdvTokenManagerPane extends ManagerPane {

    AdvTokenManagerPaneController controller;
    public AdvTokenManagerPane()
    {

        FXMLAdventureGrabber grabber = new FXMLAdventureGrabber();
        grabber.grabFXML("advTokenManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(AdvMainDatabase database)
    {
        controller.initialize(database);
    }

}
