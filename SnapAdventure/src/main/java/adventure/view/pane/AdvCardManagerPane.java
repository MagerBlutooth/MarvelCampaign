package adventure.view.pane;


import adventure.controller.manager.AdvBossManagerPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.view.pane.manager.ManagerPane;

public class AdvCardManagerPane extends ManagerPane {

    AdvBossManagerPaneController controller;
    public AdvCardManagerPane()
    {

        FXMLAdventureGrabber grabber = new FXMLAdventureGrabber();
        grabber.grabFXML("bossManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(AdvControllerDatabase database)
    {
        controller.initialize(database);
    }
}
