package view.node.editor;

import controller.ControllerDatabase;
import controller.editor.TokenEditorNodeController;
import model.thing.Card;
import model.thing.Token;
import view.node.CampaignNode;

public class TokenEditorNode extends CampaignNode {

    TokenEditorNodeController controller;
    public TokenEditorNode()
    {
        fxmlGrabber.grabFXML("tokenEditorNode.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase d, Card c)
    {
        getController().initialize(d, c);
    }


    public Token generateToken() {
        return controller.generateToken();
    }
}
