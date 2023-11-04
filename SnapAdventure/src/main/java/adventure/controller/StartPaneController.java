package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdvProfile;
import adventure.model.adventure.Adventure;
import adventure.view.node.ProfileNode;
import adventure.view.pane.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import snapMain.view.button.ButtonToolBar;

public class StartPaneController extends FullViewPaneController {

    @FXML
    AdvStartPane advStartPane;
    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    ProfileNode profile1;
    @FXML
    ProfileNode profile2;
    @FXML
    ProfileNode profile3;

    public void initialize(AdvMainDatabase dB) {
        mainDatabase = dB;
        initializeButtonToolBar();
        initializeProfiles();
    }

    @Override
    public void initializeButtonToolBar() {
        AdvMainMenuPane mainMenuPane = new AdvMainMenuPane();
        mainMenuPane.initialize(mainDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    private void initializeProfiles() {
        validateProfiles();
        profile1.setOnMousePressed(mouseEvent -> startAdventure(profile1));
        profile2.setOnMousePressed(mouseEvent -> startAdventure(profile2));
        profile3.setOnMousePressed(mouseEvent -> startAdventure(profile3));

    }

    public void validateProfiles() {
        checkProfile(AdvProfile.profile1, profile1, 1);
        checkProfile(AdvProfile.profile2, profile2, 2);
        checkProfile(AdvProfile.profile3, profile3, 3);
    }

    public void checkProfile(AdvProfile profile, ProfileNode proNode, int num) {
        proNode.generateAdventure(mainDatabase, profile, num);
    }

    private Adventure selectAdventure(ProfileNode proNode) {
        return proNode.getAdventure();
    }

    private void startAdventure(ProfileNode proNode) {
        Adventure adventure = selectAdventure(proNode);

        if (!adventure.isNewProfile() && adventure.failStateCheck()) {
            AdventureFailPane adventureFailPane = new AdventureFailPane();
            adventureFailPane.initialize(mainDatabase, adventure);
            changeScene(adventureFailPane);
        } else if (adventure.isNewProfile()) {
            AdvNewProfileOptionsPane profileOptionsPane = new AdvNewProfileOptionsPane();
            profileOptionsPane.initialize(mainDatabase, adventure, advStartPane);
            changeScene(profileOptionsPane);
        } else {
            AdventureControlPane adventureControlPane = new AdventureControlPane();
            adventureControlPane.initialize(mainDatabase, adventure);
            changeScene(adventureControlPane);
        }
    }

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }
}
