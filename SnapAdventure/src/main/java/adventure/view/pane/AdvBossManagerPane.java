package adventure.view.pane;


import adventure.controller.manager.AdvBossManagerPaneController;
import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.view.pane.manager.ManagerPane;

public class AdvBossManagerPane extends ManagerPane {

    AdvBossManagerPaneController controller;
    public AdvBossManagerPane()
    {

        FXMLAdventureGrabber grabber = new FXMLAdventureGrabber();
        grabber.grabFXML("bossManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(AdvMainDatabase database)
    {
        controller.initialize(database);
    }
}
