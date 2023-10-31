package adventure.view.node;

import adventure.controller.SectionEditorNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.base.AdvLocation;

public class SectionEditorNode extends AdvNode {

    SectionEditorNodeController controller;

    public SectionEditorNode()
    {
        fxmlAdventureGrabber.grabFXML("sectionEditorNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase d, AdvLocation s)
    {
        controller.initialize(d, s);
    }

    public AdvLocation generateSection()
    {
        return controller.generateSection();
    }
}
