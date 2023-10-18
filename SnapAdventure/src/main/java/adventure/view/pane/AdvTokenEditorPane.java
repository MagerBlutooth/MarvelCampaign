package adventure.view.pane;

import adventure.controller.AdvTokenEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.AdvToken;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.controller.MainDatabase;
import snapMain.controller.editor.LocationEditorPaneController;
import snapMain.controller.editor.TokenEditorPaneController;
import snapMain.model.target.Location;
import snapMain.view.fxml.FXMLMainGrabber;
import snapMain.view.pane.editor.EditorPane;

public class AdvTokenEditorPane extends EditorPane {

    AdvTokenEditorPaneController controller;

    public AdvTokenEditorPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("advTokenEditorPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase database, AdvToken t)
    {
        controller.initialize(database, t);
    }
}
