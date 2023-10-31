package adventure.view.node;

import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.layout.StackPane;
import snapMain.controller.editor.BasicNodeController;
import snapMain.model.target.BaseObject;


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
