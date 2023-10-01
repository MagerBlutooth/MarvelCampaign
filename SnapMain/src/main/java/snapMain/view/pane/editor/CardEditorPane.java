package snapMain.view.pane.editor;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.CardEditorPaneController;
import snapMain.model.thing.Card;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class CardEditorPane extends EditorPane {

    CardEditorPaneController controller;
    public CardEditorPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("cardEditorPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase database, ViewSize v, Card c)
    {
        controller.initialize(database, v, c);
    }
}
