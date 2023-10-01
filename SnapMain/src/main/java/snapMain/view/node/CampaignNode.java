package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.BasicNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.thing.BaseObject;
import snapMain.view.fxml.FXMLCampaignGrabber;


//Node object created for JavaFX nodes that have their own dedicated constructor, to have a built-in initialize method.
public abstract class CampaignNode extends StackPane {
    protected FXMLCampaignGrabber fxmlCampaignGrabber;

    public CampaignNode()
    {
        fxmlCampaignGrabber = new FXMLCampaignGrabber();
    }

    public void initialize(MainDatabase d, BaseObject t) {
        getController().initialize(d, t);
    }

    public <V extends BasicNodeController<MainDatabase, BaseObject>> V getController()
    {
        return fxmlCampaignGrabber.getController();
    }
}
