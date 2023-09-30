package adventure.view.pane;

import campaign.controller.MainDatabase;
import campaign.controller.editor.LocationEditorPaneController;
import campaign.model.thing.Location;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.editor.EditorPane;

public class AdvLocationEditorPane extends EditorPane {

    LocationEditorPaneController controller;

    public AdvLocationEditorPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("locationEditor.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase database, Location l)
    {
        controller.initialize(database, l);
    }
}
