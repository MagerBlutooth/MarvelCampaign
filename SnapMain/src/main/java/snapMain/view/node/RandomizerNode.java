package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.node.RandomizerNodeController;
import snapMain.model.target.Campaign;

public class RandomizerNode extends CampaignNode {

    RandomizerNodeController controller;

    public RandomizerNode()
    {
        fxmlMainGrabber.grabFXML("randomizerNode.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase cd, Campaign campaign)
    {
        controller.initialize(cd, campaign);
    }
}
