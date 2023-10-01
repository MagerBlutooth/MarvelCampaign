package adventure.view.node;

import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.controller.editor.BasicNodeController;
import snapMain.model.thing.BaseObject;
import javafx.scene.layout.StackPane;


public abstract class AdvNode extends StackPane {
    protected FXMLAdventureGrabber fxmlAdventureGrabber;

    public AdvNode()
    {
        fxmlAdventureGrabber = new FXMLAdventureGrabber();
    }

    public void initialize(AdvMainDatabase d, BaseObject t) {
        getController().initialize(d, t);
    }

    public <V extends BasicNodeController<AdvMainDatabase, BaseObject>> V getController()
    {
        return fxmlAdventureGrabber.getController();
    }
}
