package view.node.editor;

import controller.ControllerDatabase;
import controller.editor.CardEditorNodeController;
import model.thing.Card;
import view.node.CampaignNode;

public class CardEditorNode extends CampaignNode {

    CardEditorNodeController controller;

    public CardEditorNode()
    {
        fxmlGrabber.grabFXML("cardEditorNode.fxml", this);
        controller = fxmlGrabber.getController();
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
