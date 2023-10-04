package adventure.view.node;

import adventure.controller.WorldClearSelectNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.fxml.FXMLAdventureGrabber;
import adventure.view.pane.AdventureControlPane;
import snapMain.controller.MainDatabase;

public class WorldClearSelectNode extends AdvNode{

    WorldClearSelectNodeController controller;

    public WorldClearSelectNode() {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("worldClearSelectNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase d, Adventure a, AdventureControlPane aPane)
    {

        controller.initialize(d, a, aPane);
    }

}
