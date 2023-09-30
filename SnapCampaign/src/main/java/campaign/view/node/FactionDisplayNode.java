package campaign.view.node;

import campaign.controller.MainDatabase;
import campaign.controller.node.FactionDisplayNodeController;
import javafx.scene.layout.StackPane;
import campaign.model.thing.Faction;
import campaign.view.fxml.FXMLCampaignGrabber;

public class FactionDisplayNode extends StackPane {

    FactionDisplayNodeController controller;

    public FactionDisplayNode() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("factionDisplayNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    //Display Node should be "blind" if Captain status should be hidden
    public void initialize(MainDatabase d, Faction f, boolean blind)
    {
        controller.initialize(d, f, blind);
    }
}
