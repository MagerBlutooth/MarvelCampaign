package adventure.controller;

import adventure.model.AdvControllerDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.AdventureConstants;
import adventure.model.AdventureDatabase;
import adventure.view.node.ProfileNode;
import adventure.view.pane.AdvMainMenuPane;
import adventure.view.pane.AdventureControlPane;
import campaign.view.button.ButtonToolBar;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class AdvStartPaneController extends AdvPaneController {

    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    ProfileNode profile1;
    @FXML
    ProfileNode profile2;
    @FXML
    ProfileNode profile3;

    public void initialize(AdvControllerDatabase dB)
    {
        controllerDatabase = dB;
        buttonToolBar.initialize(new AdvMainMenuPane());
        initializeProfiles();
    }

    private void initializeProfiles() {
        validateProfiles();
        profile1.setOnMousePressed(mouseEvent -> startAdventure(AdventureConstants.PROFILE_1));
        profile2.setOnMousePressed(mouseEvent -> startAdventure(AdventureConstants.PROFILE_2));
        profile3.setOnMousePressed(mouseEvent -> startAdventure(AdventureConstants.PROFILE_3));

    }

    private void validateProfiles() {
        checkProfile(AdventureConstants.PROFILE_1, profile1, 1);
        checkProfile(AdventureConstants.PROFILE_2, profile2, 2);
        checkProfile(AdventureConstants.PROFILE_3, profile3, 3);

    }

    private void checkProfile(String profile, ProfileNode proNode, int num) {
        AdventureDatabase adventureDatabase = new AdventureDatabase(controllerDatabase);
        Adventure adventure = new Adventure(adventureDatabase, profile);
        String name = adventure.getProfileName();
        if(name == null)
            name = "Empty";
            proNode.initialize(name, "1");
    }

    //TODO: Create dialog pane for entering profileName and showing starting team
    private Adventure initializeAdventure(String profile)
    {
        AdventureDatabase adventureDatabase = new AdventureDatabase(controllerDatabase);
        return new Adventure(adventureDatabase, profile, "Chara");
    }

    private void startAdventure(String profile) {
        AdventureControlPane adventureControlPane = new AdventureControlPane();
        Adventure adventure = initializeAdventure(profile);
        adventureControlPane.initialize(controllerDatabase, adventure);
        changeScene(adventureControlPane);
    }

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }
}
