package adventure.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.LocationEditorPaneController;
import snapMain.model.target.Location;
import snapMain.view.fxml.FXMLMainGrabber;
import snapMain.view.pane.editor.EditorPane;

public class AdvLocationEditorPane extends EditorPane {

    LocationEditorPaneController controller;

    public AdvLocationEditorPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("locationEditor.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase database, Location l)
    {
        controller.initialize(database, l);
    }
}
