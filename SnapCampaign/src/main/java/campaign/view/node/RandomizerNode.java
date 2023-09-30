package campaign.view.node;

import campaign.controller.MainDatabase;
import campaign.controller.node.RandomizerNodeController;
import campaign.model.thing.Campaign;

public class RandomizerNode extends CampaignNode {

    RandomizerNodeController controller;

    public RandomizerNode()
    {
        fxmlCampaignGrabber.grabFXML("randomizerNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase cd, Campaign campaign)
    {
        controller.initialize(cd, campaign);
    }
}
