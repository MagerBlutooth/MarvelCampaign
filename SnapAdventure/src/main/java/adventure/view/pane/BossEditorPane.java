package adventure.view.pane;

import adventure.controller.BossEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.editor.EditorPane;

public class BossEditorPane extends EditorPane {

    BossEditorPaneController controller;
    public BossEditorPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("bossEditorPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase database, AdvCard b)
    {
        controller.initialize(database, b);
    }
}
