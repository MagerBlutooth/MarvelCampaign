package adventure.view.pane;

import adventure.controller.manager.AdvLocationManagerPaneController;
import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.manager.ManagerPane;

public class AdvLocationManagerPane extends ManagerPane {

    AdvLocationManagerPaneController controller;
    public AdvLocationManagerPane()
    {

        FXMLAdventureGrabber grabber = new FXMLAdventureGrabber();
        grabber.grabFXML("sectionManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(AdvMainDatabase database)
    {
        controller.initialize(database);
    }

}
