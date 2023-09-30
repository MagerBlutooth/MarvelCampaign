package adventure.view.pane;

import adventure.controller.manager.AdvSectionManagerPaneController;
import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.view.pane.manager.ManagerPane;

public class AdvLocationManagerPane extends ManagerPane {

    AdvSectionManagerPaneController controller;
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
