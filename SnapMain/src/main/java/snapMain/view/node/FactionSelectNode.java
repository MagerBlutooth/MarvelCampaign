package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.node.FactionSelectNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.thing.Faction;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class FactionSelectNode extends StackPane {

    FactionSelectNodeController controller;

    public FactionSelectNode() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("factionSelectNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    //Select Node should be "blind" if Captain status should be hidden
    public void initialize(MainDatabase d, Faction f, boolean blind)
    {
        controller.initialize(d, f, blind);
    }

    public void refresh() {
        controller.refresh();
    }
}
