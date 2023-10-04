package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.node.FactionSelectNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.target.Faction;
import snapMain.view.fxml.FXMLMainGrabber;

public class FactionSelectNode extends StackPane {

    FactionSelectNodeController controller;

    public FactionSelectNode() {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("factionSelectNode.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    //Select Node should be "blind" if Captain status should be hidden
    public void initialize(MainDatabase d, Faction f, boolean blind)
    {
        controller.initialize(d, f, blind);
    }

    public void refresh() {
        controller.refresh();
    }
}
