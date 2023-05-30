package view.node;

import controller.ControllerDatabase;
import controller.editor.CampaignNodeController;
import javafx.scene.layout.StackPane;
import model.thing.Thing;
import view.fxml.FXMLGrabber;


//Node object created for JavaFX nodes that have their own dedicated constructor, to have a built-in initialize method.
public abstract class CampaignNode extends StackPane {
    protected FXMLGrabber fxmlGrabber;

    public CampaignNode()
    {
        fxmlGrabber = new FXMLGrabber();
    }

    public void initialize(ControllerDatabase d, Thing t) {
        getController().initialize(d, t);
    }

    public <V extends CampaignNodeController<Thing>> V getController()
    {
        return fxmlGrabber.getController();
    }
}
