package adventure.view.node;

import adventure.controller.BossEditorNodeController;
import adventure.model.AdvControllerDatabase;
import adventure.model.Boss;

public class BossEditorNode extends AdvNode {

    BossEditorNodeController controller;

    public BossEditorNode()
    {
        fxmlAdventureGrabber.grabFXML("bossEditorNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvControllerDatabase d, Boss b)
    {
        controller.initialize(d, b);
    }

    public Boss generateBoss()
    {
        return controller.generateBoss();
    }
}
