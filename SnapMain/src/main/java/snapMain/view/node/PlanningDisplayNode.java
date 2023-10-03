package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.node.PlanningDisplayNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.target.Faction;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class PlanningDisplayNode extends StackPane {

    PlanningDisplayNodeController controller;

    public PlanningDisplayNode() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("planningDisplayNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase d, Faction f)
    {
        controller.initialize(d, f);
    }
}
