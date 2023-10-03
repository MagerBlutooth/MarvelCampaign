package snapMain.view.node.editor;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.CardEditorNodeController;
import snapMain.model.target.Card;
import snapMain.view.node.CampaignNode;

public class CardEditorNode extends CampaignNode {

    CardEditorNodeController controller;

    public CardEditorNode()
    {
        fxmlCampaignGrabber.grabFXML("cardEditorNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase d, Card c)
    {
        controller.initialize(d, c);
    }

    public Card generateCard()
    {
        return controller.generateCard();
    }
}
