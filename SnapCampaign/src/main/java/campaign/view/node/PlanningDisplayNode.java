package campaign.view.node;

import campaign.controller.ControllerDatabase;
import campaign.controller.node.PlanningDisplayNodeController;
import javafx.scene.layout.StackPane;
import campaign.model.thing.Faction;
import campaign.view.fxml.FXMLCampaignGrabber;

public class PlanningDisplayNode extends StackPane {

    PlanningDisplayNodeController controller;

    public PlanningDisplayNode() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("planningDisplayNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase d, Faction f)
    {
        controller.initialize(d, f);
    }
}
