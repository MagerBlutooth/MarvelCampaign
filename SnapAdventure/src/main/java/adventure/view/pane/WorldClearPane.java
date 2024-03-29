package adventure.view.pane;

import adventure.controller.WorldClearPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.FullViewPane;

public class WorldClearPane extends FullViewPane {

    WorldClearPaneController controller;
    public WorldClearPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("worldClearPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase db, Adventure a, AdventureControlPane cPane)
    {
        controller.initialize(db, a, cPane);
    }
}
