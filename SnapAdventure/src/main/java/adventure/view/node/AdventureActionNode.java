package adventure.view.node;

import adventure.controller.AdventureActionNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.fxml.FXMLAdventureGrabber;
import adventure.view.pane.AdventureControlPane;
import javafx.scene.layout.StackPane;

public class AdventureActionNode extends StackPane {

    AdventureActionNodeController controller;

    public AdventureActionNode()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("adventureActionNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase database, Adventure a, AdventureControlPane aControlPane) {
        controller.initialize(database, a, aControlPane);
    }
}
