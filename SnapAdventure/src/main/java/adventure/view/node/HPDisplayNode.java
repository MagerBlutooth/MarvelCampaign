package adventure.view.node;

import adventure.controller.HPDisplayNodeController;
import adventure.model.target.Enemy;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.StackPane;

public class HPDisplayNode extends StackPane {
    HPDisplayNodeController controller;

    public HPDisplayNode()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("hpDisplayNode.fxml", this);
        controller = adventureGrabber.getController();
    }

    public void initialize(Enemy b)
    {
        controller.initialize(b);
    }

    public void update() {
        controller.refresh();
    }

    public IntegerProperty getHPProperty()
    {
        return controller.getHPProperty();
    }
}
