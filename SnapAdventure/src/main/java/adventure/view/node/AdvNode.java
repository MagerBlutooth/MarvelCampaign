package adventure.view.node;

import adventure.model.AdvControllerDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.ControllerDatabase;
import campaign.controller.editor.BasicNodeController;
import campaign.model.thing.Thing;
import campaign.view.fxml.FXMLCampaignGrabber;
import javafx.scene.layout.StackPane;


//Node object created for JavaFX nodes that have their own dedicated constructor, to have a built-in initialize method.
public abstract class AdvNode extends StackPane {
    protected FXMLAdventureGrabber fxmlAdventureGrabber;

    public AdvNode()
    {
        fxmlAdventureGrabber = new FXMLAdventureGrabber();
    }

    public void initialize(AdvControllerDatabase d, Thing t) {
        getController().initialize(d, t);
    }

    public <V extends BasicNodeController<AdvControllerDatabase, Thing>> V getController()
    {
        return fxmlAdventureGrabber.getController();
    }
}
