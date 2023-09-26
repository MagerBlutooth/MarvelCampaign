package adventure.view.node;

import adventure.controller.manager.ProfileNodeController;

public class ProfileNode extends AdvNode {

    ProfileNodeController controller;

    public ProfileNode()
    {
        fxmlAdventureGrabber.grabFXML("profileNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }
}
