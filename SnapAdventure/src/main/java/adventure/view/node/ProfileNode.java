package adventure.view.node;

import adventure.controller.AdvStartPaneController;
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

    public void initialize(String profileString, String profileNum, String profileFile)
    {
        controller.initialize(profileString, profileNum, profileFile);
    }

    public void initialize(AdvMainDatabase mainDB, Adventure adventure, String profileNum, String profileFile,
                           AdvStartPaneController startPaneController)
    {
        controller.initialize(mainDB, adventure, profileNum, profileFile, startPaneController);
    }

    public void setText(String profileName) {
        controller.setText(profileName);
    }
}
