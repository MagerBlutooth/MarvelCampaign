package adventure.view.node;

import adventure.controller.WorldDisplayNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.World;
import adventure.view.fxml.FXMLAdventureGrabber;
import adventure.view.pane.AdventureControlPane;
import javafx.scene.layout.StackPane;

public class WorldDisplayNode extends StackPane {

    WorldDisplayNodeController controller;

    public WorldDisplayNode() {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("worldDisplayNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase d, World w, AdventureControlPane aPane)
    {
        controller.initialize(d, w, aPane);
    }

    public void refresh(World w) {
        controller.refresh(w);
    }

    public void revealNextSection(int nextSection) {
        controller.revealNextSection(nextSection);
    }
}
