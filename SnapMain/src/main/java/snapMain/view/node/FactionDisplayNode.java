package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.node.FactionDisplayNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.thing.Faction;
import snapMain.view.fxml.FXMLCampaignGrabber;

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
