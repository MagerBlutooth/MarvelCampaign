package campaign.view.pane.editor;

import campaign.controller.ControllerDatabase;
import campaign.controller.editor.TokenEditorPaneController;
import campaign.model.thing.Token;
import campaign.view.ViewSize;
import campaign.view.fxml.FXMLCampaignGrabber;

public class TokenEditorPane extends EditorPane {

    TokenEditorPaneController controller;
    public TokenEditorPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("tokenEditorPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase database, ViewSize v, Token t)
    {
        controller.initialize(database, v, t);
    }
}
