package snapMain.view.pane.editor;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.LocationEditorPaneController;
import snapMain.model.target.Location;
import snapMain.view.fxml.FXMLCampaignGrabber;

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
