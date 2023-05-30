package view.node;

import controller.ControllerDatabase;
import controller.node.RandomizerNodeController;
import model.thing.Campaign;

public class RandomizerNode extends CampaignNode {

    RandomizerNodeController controller;

    public RandomizerNode()
    {
        fxmlGrabber.grabFXML("randomizerNode.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase cd, Campaign campaign)
    {
        controller.initialize(cd, campaign);
    }
}
