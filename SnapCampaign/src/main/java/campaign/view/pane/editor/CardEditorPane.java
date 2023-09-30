package campaign.view.pane.editor;

import campaign.controller.MainDatabase;
import campaign.controller.editor.CardEditorPaneController;
import campaign.model.thing.Card;
import campaign.view.ViewSize;
import campaign.view.fxml.FXMLCampaignGrabber;

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
