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
        profile1.initialize("Empty", "1", initializeAdventure(AdventureConstants.PROFILE_1));
        profile2.initialize("Empty", "2", initializeAdventure(AdventureConstants.PROFILE_2));
        profile3.initialize("Empty", "3", initializeAdventure(AdventureConstants.PROFILE_3));
        profile1.setOnMousePressed(mouseEvent -> startAdventure(AdventureConstants.PROFILE_1));
        profile2.setOnMousePressed(mouseEvent -> startAdventure(AdventureConstants.PROFILE_2));
        profile3.setOnMousePressed(mouseEvent -> startAdventure(AdventureConstants.PROFILE_3));
    }

    private Adventure initializeAdventure(String profile)
    {
        AdventureDatabase adventureDatabase = new AdventureDatabase(controllerDatabase);
        return new Adventure(adventureDatabase, profile);
    }

    private void startAdventure(String profile) {
        AdventureControlPane adventureControlPane = new AdventureControlPane();
        Adventure adventure = initializeAdventure(profile);
        adventureControlPane.initialize(controllerDatabase, adventure);
    }

    @Override
    public Scene getCurrentScene() {
        return null;
    }
}
