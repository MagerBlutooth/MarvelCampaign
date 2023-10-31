package adventure.view.pane;


import adventure.controller.manager.AdvCardManagerPaneController;
import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.manager.ManagerPane;

public class AdvCardManagerPane extends ManagerPane {

    AdvCardManagerPaneController controller;
    public AdvCardManagerPane()
    {

        FXMLAdventureGrabber grabber = new FXMLAdventureGrabber();
        grabber.grabFXML("advCardManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(AdvMainDatabase database)
    {
        controller.initialize(database);
    }
}
