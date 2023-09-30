package campaign.view.pane;

import campaign.controller.MainDatabase;
import campaign.controller.editor.LocationEditorPaneController;
import campaign.model.thing.Location;
import campaign.view.fxml.FXMLCampaignGrabber;

public class LocationEditorPane extends BasicPane {

    LocationEditorPaneController controller;
    public LocationEditorPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("locationEditorPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase database, Location l)
    {
        controller.initialize(database, l);
    }
}
