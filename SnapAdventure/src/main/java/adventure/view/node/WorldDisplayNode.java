package adventure.view.node;

import adventure.controller.WorldDisplayNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.World;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.layout.StackPane;

public class WorldDisplayNode extends StackPane {

    WorldDisplayNodeController controller;

    public WorldDisplayNode() {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("worldDisplayNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase d, World w, int worldNum, int sectionNum)
    {
        controller.initialize(d, w, worldNum, sectionNum);
    }
}
