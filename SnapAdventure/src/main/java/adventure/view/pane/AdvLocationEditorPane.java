package adventure.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.LocationEditorPaneController;
import snapMain.model.thing.Location;
import snapMain.view.fxml.FXMLCampaignGrabber;
import snapMain.view.pane.editor.EditorPane;

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
