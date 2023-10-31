package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.WatcherControlPaneController;
import snapMain.controller.node.FreeAgentSelectNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.target.Faction;
import snapMain.view.fxml.FXMLMainGrabber;

public class FreeAgentSelectNode extends StackPane {

    FreeAgentSelectNodeController controller;

    public FreeAgentSelectNode() {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("freeAgentSelectNode.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase d, WatcherControlPaneController c, Faction f, String s, String h)
    {
        controller.initialize(d, c, f, s, h);
    }

    public void refresh() {
        controller.refresh();
    }
}
