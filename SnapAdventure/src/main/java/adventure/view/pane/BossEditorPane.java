package adventure.view.pane;

import adventure.controller.BossEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.Boss;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.view.pane.editor.EditorPane;

public class BossEditorPane extends EditorPane {

    BossEditorPaneController controller;
    public BossEditorPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("bossEditorPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase database, Boss b)
    {
        controller.initialize(database, b);
    }
}
