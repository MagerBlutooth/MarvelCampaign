package campaign.view.pane.editor;

import campaign.controller.MainDatabase;
import campaign.controller.editor.LocationEditorPaneController;
import campaign.model.thing.Location;
import campaign.view.fxml.FXMLCampaignGrabber;

public class LocationEditorPane extends EditorPane {

    LocationEditorPaneController controller;

    public LocationEditorPane()
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
