package adventure.view.pane;

import adventure.controller.SectionEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.base.AdvLocation;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.editor.EditorPane;

public class SectionEditorPane extends EditorPane {

    SectionEditorPaneController controller;
    public SectionEditorPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("sectionEditorPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase database, AdvLocation s)
    {
        controller.initialize(database, s);
    }
}
