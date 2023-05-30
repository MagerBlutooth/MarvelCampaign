package view.node;

import controller.ControllerDatabase;
import controller.WatcherControlPaneController;
import controller.node.FreeAgentSelectNodeController;
import javafx.scene.layout.StackPane;
import model.thing.Faction;
import view.fxml.FXMLGrabber;

public class FreeAgentSelectNode extends StackPane {

    FreeAgentSelectNodeController controller;

    public FreeAgentSelectNode() {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("freeAgentSelectNode.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase d, WatcherControlPaneController c, Faction f, String s, String h)
    {
        controller.initialize(d, c, f, s, h);
    }

    public void refresh() {
        controller.refresh();
    }
}
