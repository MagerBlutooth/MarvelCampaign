package campaign.view.node;

import campaign.controller.ControllerDatabase;
import campaign.controller.editor.CampaignNodeController;
import javafx.scene.layout.StackPane;
import campaign.model.thing.Thing;
import campaign.view.fxml.FXMLCampaignGrabber;


//Node object created for JavaFX nodes that have their own dedicated constructor, to have a built-in initialize method.
public abstract class CampaignNode extends StackPane {
    protected FXMLCampaignGrabber fxmlCampaignGrabber;

    public CampaignNode()
    {
        fxmlCampaignGrabber = new FXMLCampaignGrabber();
    }

    public void initialize(ControllerDatabase d, Thing t) {
        getController().initialize(d, t);
    }

    public <V extends CampaignNodeController<Thing>> V getController()
    {
        return fxmlCampaignGrabber.getController();
    }
}
