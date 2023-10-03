package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.WatcherControlPaneController;
import snapMain.controller.node.FreeAgentSelectNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.target.Faction;
import snapMain.view.fxml.FXMLCampaignGrabber;

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
