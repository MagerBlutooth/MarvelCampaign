package view.pane.editor;

import controller.ControllerDatabase;
import controller.editor.LocationEditorPaneController;
import model.thing.Location;
import view.fxml.FXMLGrabber;

public class LocationEditorPane extends EditorPane {

    LocationEditorPaneController controller;

    public LocationEditorPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("locationEditor.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase database, Location l)
    {
        controller.initialize(database, l);
    }
}
