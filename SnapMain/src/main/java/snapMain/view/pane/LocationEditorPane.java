package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.LocationEditorPaneController;
import snapMain.model.target.Location;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class LocationEditorPane extends FullViewPane {

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
