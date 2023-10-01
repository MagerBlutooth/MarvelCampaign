package adventure.view.node;

import adventure.controller.BossEditorNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvCard;

public class BossEditorNode extends AdvNode {

    BossEditorNodeController controller;

    public BossEditorNode()
    {
        fxmlAdventureGrabber.grabFXML("bossEditorNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase d, AdvCard b)
    {
        controller.initialize(d, b);
    }

    public AdvCard generateBoss()
    {
        return controller.generateBoss();
    }
}
