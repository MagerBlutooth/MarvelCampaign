package view.pane;

import controller.ControllerDatabase;
import controller.editor.LocationEditorPaneController;
import model.thing.Location;
import view.fxml.FXMLGrabber;
import view.pane.editor.EditorPane;

public class LocationEditorPane extends CampaignPane {

    LocationEditorPaneController controller;
    public LocationEditorPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("locationEditorPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase database, Location l)
    {
        controller.initialize(database, l);
    }
}
