package adventure.view.node;

import adventure.controller.manager.ProfileNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.AdvProfile;
import adventure.model.adventure.Adventure;

public class ProfileNode extends AdvNode {

    ProfileNodeController controller;

    public ProfileNode()
    {
        fxmlAdventureGrabber.grabFXML("profileNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(String profileString, int profileNum, AdvProfile profile)
    {
        controller.initialize(profileString, profileNum, profile);
    }

    public void initialize(AdvMainDatabase mainDB, Adventure adventure, int profileNum, AdvProfile profile)
    {
        controller.initialize(mainDB, adventure, profileNum, profile);
    }

    public void setText(String profileName) {
        controller.setText(profileName);
    }
    public Adventure getAdventure() {
        return controller.getAdventure();
    }

    public void generateAdventure(AdvMainDatabase mainDatabase, AdvProfile profile, int profileNum) {
        controller.generateAdventure(mainDatabase, profile, profileNum);
    }
}
