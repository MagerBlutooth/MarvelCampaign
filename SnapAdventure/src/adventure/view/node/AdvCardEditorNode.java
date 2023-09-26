package adventure.view.node;

import adventure.controller.AdvCardEditorNodeController;
import adventure.model.Boss;
import campaign.controller.ControllerDatabase;
import campaign.model.thing.Card;
import campaign.view.node.CampaignNode;

public class AdvCardEditorNode extends AdvNode {

    AdvCardEditorNodeController controller;

    public AdvCardEditorNode()
    {
        fxmlAdventureGrabber.grabFXML("cardEditorNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(ControllerDatabase d, Card c)
    {
        controller.initialize(d, c);
    }

    public Boss generateBoss()
    {
        return controller.generateBoss();
    }
}
