package campaign.view.node.editor;

import campaign.controller.MainDatabase;
import campaign.controller.editor.TokenEditorNodeController;
import campaign.model.thing.Card;
import campaign.model.thing.Token;
import campaign.view.node.CampaignNode;

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
