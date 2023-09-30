package adventure.view.node;

import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.editor.BasicNodeController;
import campaign.model.thing.Thing;
import javafx.scene.layout.StackPane;


public abstract class AdvNode extends StackPane {
    protected FXMLAdventureGrabber fxmlAdventureGrabber;

    public AdvNode()
    {
        fxmlAdventureGrabber = new FXMLAdventureGrabber();
    }

    public void initialize(AdvMainDatabase d, Thing t) {
        getController().initialize(d, t);
    }

    public <V extends BasicNodeController<AdvMainDatabase, Thing>> V getController()
    {
        return fxmlAdventureGrabber.getController();
    }
}
