package adventure.view.node;

import adventure.controller.AdvTokenEditorNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.base.AdvToken;

public class AdvTokenEditorNode extends AdvNode {

    AdvTokenEditorNodeController controller;

    public AdvTokenEditorNode()
    {
        fxmlAdventureGrabber.grabFXML("advTokenEditorNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase d, AdvToken b)
    {
        controller.initialize(d, b);
    }

    public AdvToken generateToken()
    {
        return controller.generateToken();
    }
}
