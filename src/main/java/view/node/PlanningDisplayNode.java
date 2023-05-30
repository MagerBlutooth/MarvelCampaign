package view.node;

import controller.ControllerDatabase;
import controller.node.FactionDisplayNodeController;
import controller.node.PlanningDisplayNodeController;
import javafx.scene.layout.StackPane;
import model.thing.Faction;
import view.fxml.FXMLGrabber;

public class PlanningDisplayNode extends StackPane {

    PlanningDisplayNodeController controller;

    public PlanningDisplayNode() {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("planningDisplayNode.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase d, Faction f)
    {
        controller.initialize(d, f);
    }
}
