package adventure.view.pane;

import adventure.controller.AdventureControlPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.editor.EditorPane;

public class AdventureControlPane extends EditorPane {

    AdventureControlPaneController controller;

    public AdventureControlPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("adventureControlPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvControllerDatabase db, Adventure adventure)
    {
        controller.initialize(db, adventure);
    }
}
