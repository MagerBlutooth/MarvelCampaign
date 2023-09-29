package adventure.view.node;

import adventure.controller.manager.ProfileNodeController;
import adventure.model.AdvControllerDatabase;
import adventure.model.adventure.Adventure;

public class ProfileNode extends AdvNode {

    ProfileNodeController controller;

    public ProfileNode()
    {
        fxmlAdventureGrabber.grabFXML("profileNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(String profileString, String profileNum)
    {
        controller.initialize(profileString, profileNum);
    }
}
