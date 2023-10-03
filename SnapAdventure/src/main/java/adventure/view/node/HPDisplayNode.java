package adventure.view.node;

import adventure.controller.HPDisplayNodeController;
import adventure.model.thing.Boss;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.layout.StackPane;

public class HPDisplayNode extends StackPane {
    HPDisplayNodeController controller;

    public HPDisplayNode()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("hpDisplayNode.fxml", this);
        controller = adventureGrabber.getController();
    }

    public void initialize(Boss b)
    {
        controller.initialize(b);
    }

    public void update() {
        controller.refresh();
    }
}
