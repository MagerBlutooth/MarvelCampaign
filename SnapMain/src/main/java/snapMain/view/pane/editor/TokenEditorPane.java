package snapMain.view.pane.editor;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.TokenEditorPaneController;
import snapMain.model.thing.Token;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class TokenEditorPane extends EditorPane {

    TokenEditorPaneController controller;
    public TokenEditorPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("tokenEditorPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase database, ViewSize v, Token t)
    {
        controller.initialize(database, v, t);
    }
}
