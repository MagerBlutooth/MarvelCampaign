package adventure.view.pane;

import adventure.controller.AdventureControlPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.model.Adventure;
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
