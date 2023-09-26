package adventure.view.pane;

import adventure.controller.AdvCardEditorPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.model.Boss;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.ControllerDatabase;
import campaign.controller.editor.CardEditorPaneController;
import campaign.model.thing.Card;
import campaign.view.ViewSize;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.editor.EditorPane;

public class AdvCardEditorPane extends EditorPane {

    AdvCardEditorPaneController controller;
    public AdvCardEditorPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("cardEditorPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvControllerDatabase database, ViewSize v, Card c)
    {
        controller.initialize(database, v, c);
    }
}
