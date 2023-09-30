package campaign.view.node;

import campaign.controller.MainDatabase;
import campaign.controller.WatcherControlPaneController;
import campaign.controller.node.FreeAgentSelectNodeController;
import javafx.scene.layout.StackPane;
import campaign.model.thing.Faction;
import campaign.view.fxml.FXMLCampaignGrabber;

public class FreeAgentSelectNode extends StackPane {

    FreeAgentSelectNodeController controller;

    public FreeAgentSelectNode() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("freeAgentSelectNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase d, WatcherControlPaneController c, Faction f, String s, String h)
    {
        controller.initialize(d, c, f, s, h);
    }

    public void refresh() {
        controller.refresh();
    }
}
