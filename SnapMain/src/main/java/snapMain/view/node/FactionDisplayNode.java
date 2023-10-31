package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.node.FactionDisplayNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.target.Faction;
import snapMain.view.fxml.FXMLMainGrabber;

public class FactionDisplayNode extends StackPane {

    FactionDisplayNodeController controller;

    public FactionDisplayNode() {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("factionDisplayNode.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    //Display Node should be "blind" if Captain status should be hidden
    public void initialize(MainDatabase d, Faction f, boolean blind)
    {
        controller.initialize(d, f, blind);
    }
}
