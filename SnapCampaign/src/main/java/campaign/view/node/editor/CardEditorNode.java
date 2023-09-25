package campaign.view.node.editor;

import campaign.controller.ControllerDatabase;
import campaign.controller.editor.CardEditorNodeController;
import campaign.model.thing.Card;
import campaign.view.node.CampaignNode;

public class CardEditorNode extends CampaignNode {

    CardEditorNodeController controller;

    public CardEditorNode()
    {
        fxmlCampaignGrabber.grabFXML("cardEditorNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase d, Card c)
    {
        controller.initialize(d, c);
    }

    public Card generateCard()
    {
        return controller.generateCard();
    }
}
