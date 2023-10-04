package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.node.PlanningDisplayNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.target.Faction;
import snapMain.view.fxml.FXMLMainGrabber;

public class PlanningDisplayNode extends StackPane {

    PlanningDisplayNodeController controller;

    public PlanningDisplayNode() {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("planningDisplayNode.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase d, Faction f)
    {
        controller.initialize(d, f);
    }
}
