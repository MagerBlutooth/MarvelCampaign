package adventure.view.node;

import adventure.controller.manager.ProfileNodeController;
import adventure.model.AdvMainDatabase;
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

    public void initialize(AdvMainDatabase mainDB, Adventure adventure, String profileNum)
    {
        controller.initialize(mainDB, adventure, profileNum);
    }

    public void setText(String profileName) {
        controller.setText(profileName);
    }
}
