package adventure.view.node;

import adventure.controller.AdvCardEditorNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.base.AdvCard;

public class AdvCardEditorNode extends AdvNode {

    AdvCardEditorNodeController controller;

    public AdvCardEditorNode()
    {
        fxmlAdventureGrabber.grabFXML("advCardEditorNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase d, AdvCard b)
    {
        controller.initialize(d, b);
    }

    public AdvCard generateBoss()
    {
        return controller.generateAdvCard();
    }
}
