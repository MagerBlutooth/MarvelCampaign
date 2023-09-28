package adventure.view.node;

import adventure.controller.SectionEditorNodeController;
import adventure.model.AdvControllerDatabase;
import adventure.model.Section;

public class SectionEditorNode extends AdvNode {

    SectionEditorNodeController controller;

    public SectionEditorNode()
    {
        fxmlAdventureGrabber.grabFXML("sectionEditorNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvControllerDatabase d, Section s)
    {
        controller.initialize(d, s);
    }

    public Section generateSection()
    {
        return controller.generateSection();
    }
}
