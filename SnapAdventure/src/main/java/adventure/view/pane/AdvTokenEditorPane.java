package adventure.view.pane;

import adventure.controller.AdvTokenEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.base.AdvToken;
import adventure.view.fxml.FXMLAdventureGrabber;
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
