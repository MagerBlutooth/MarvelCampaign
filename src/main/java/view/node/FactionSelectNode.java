package view.node;

import controller.node.FactionSelectNodeController;
import controller.ControllerDatabase;
import javafx.scene.layout.StackPane;
import model.thing.Card;
import model.thing.Faction;
import view.fxml.FXMLGrabber;

import java.util.List;

public class FactionSelectNode extends StackPane {

    FactionSelectNodeController controller;

    public FactionSelectNode() {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("factionSelectNode.fxml", this);
        controller = fxmlGrabber.getController();
    }

    //Select Node should be "blind" if Captain status should be hidden
    public void initialize(ControllerDatabase d, Faction f, boolean blind)
    {
        controller.initialize(d, f, blind);
    }

    public void refresh() {
        controller.refresh();
    }
}
