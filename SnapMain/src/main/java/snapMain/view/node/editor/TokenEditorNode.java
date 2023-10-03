package snapMain.view.node.editor;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.TokenEditorNodeController;
import snapMain.model.target.Card;
import snapMain.model.target.Token;
import snapMain.view.node.CampaignNode;

public class TokenEditorNode extends CampaignNode {

    TokenEditorNodeController controller;
    public TokenEditorNode()
    {
        fxmlCampaignGrabber.grabFXML("tokenEditorNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase d, Card c)
    {
        getController().initialize(d, c);
    }


    public Token generateToken() {
        return controller.generateToken();
    }
}
