package adventure.view.node;

import adventure.controller.DiceNodeController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.layout.StackPane;
import snapMain.controller.MainDatabase;

public class DiceNode extends StackPane {

    DiceNodeController controller;

    public DiceNode()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("diceNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
