package campaign.view.node;

import campaign.controller.MainDatabase;
import campaign.controller.node.FactionSelectNodeController;
import javafx.scene.layout.StackPane;
import campaign.model.thing.Faction;
import campaign.view.fxml.FXMLCampaignGrabber;

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
