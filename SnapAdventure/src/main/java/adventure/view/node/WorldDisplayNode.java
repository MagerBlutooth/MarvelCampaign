package adventure.view.node;

import adventure.controller.TeamDisplayNodeController;
import adventure.controller.WorldDisplayNodeController;
import adventure.model.AdvControllerDatabase;
import adventure.model.Section;
import adventure.model.Team;
import adventure.model.World;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.ControllerDatabase;
import javafx.scene.layout.StackPane;

public class WorldDisplayNode extends StackPane {

    WorldDisplayNodeController controller;

    public WorldDisplayNode() {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("worldDisplayNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvControllerDatabase d, World w)
    {
        controller.initialize(d, w);
    }
}
