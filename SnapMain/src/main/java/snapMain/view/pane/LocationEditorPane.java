package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.LocationEditorPaneController;
import snapMain.model.target.Location;
import snapMain.view.fxml.FXMLMainGrabber;

public class LocationEditorPane extends FullViewPane {

    LocationEditorPaneController controller;
    public LocationEditorPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("locationEditorPane.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase database, Location l)
    {
        controller.initialize(database, l);
    }
}
