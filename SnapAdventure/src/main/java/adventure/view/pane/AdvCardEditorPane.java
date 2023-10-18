package adventure.view.pane;

import adventure.controller.AdvCardEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.AdvCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.editor.EditorPane;

public class AdvCardEditorPane extends EditorPane {

    AdvCardEditorPaneController controller;
    public AdvCardEditorPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("advCardEditorPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase database, AdvCard b)
    {
        controller.initialize(database, b);
    }
}
