package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.BasicNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.target.BaseObject;
import snapMain.view.fxml.FXMLMainGrabber;


//Node object created for JavaFX nodes that have their own dedicated constructor, to have a built-in initialize method.
public abstract class CampaignNode extends StackPane {
    protected FXMLMainGrabber fxmlMainGrabber;

    public CampaignNode()
    {
        fxmlMainGrabber = new FXMLMainGrabber();
    }

    public void initialize(MainDatabase d, BaseObject t) {
        getController().initialize(d, t);
    }

    public <V extends BasicNodeController<MainDatabase, BaseObject>> V getController()
    {
        return fxmlMainGrabber.getController();
    }
}
