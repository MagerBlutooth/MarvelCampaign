package view.node;

import controller.ControllerDatabase;
import controller.node.FactionDisplayNodeController;
import javafx.scene.layout.StackPane;
import model.thing.Faction;
import view.fxml.FXMLGrabber;

public class FactionDisplayNode extends StackPane {

    FactionDisplayNodeController controller;

    public FactionDisplayNode() {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("factionDisplayNode.fxml", this);
        controller = fxmlGrabber.getController();
    }

    //Display Node should be "blind" if Captain status should be hidden
    public void initialize(ControllerDatabase d, Faction f, boolean blind)
    {
        controller.initialize(d, f, blind);
    }
}
