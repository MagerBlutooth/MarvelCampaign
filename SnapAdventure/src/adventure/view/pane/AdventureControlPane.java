package adventure.view.pane;

import adventure.controller.AdvCardEditorPaneController;
import adventure.controller.AdventureControlPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.model.Adventure;
import campaign.controller.ControllerDatabase;
import campaign.controller.WatcherControlPaneController;
import campaign.model.thing.Campaign;
import campaign.model.thing.Card;
import campaign.view.ViewSize;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.editor.EditorPane;

public class AdventureControlPane extends EditorPane {

    AdventureControlPaneController controller;

    public AdventureControlPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("adventureControlPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(AdvControllerDatabase db, Adventure adventure)
    {
        controller.initialize(db, adventure);
    }
}
