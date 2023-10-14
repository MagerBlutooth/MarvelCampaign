package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.node.ProfileNode;
import adventure.view.pane.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import snapMain.view.button.ButtonToolBar;

import java.util.concurrent.ConcurrentHashMap;

import static adventure.model.AdventureConstants.EMPTY_PROFILE;

public class AdvStartPaneController extends AdvPaneController {

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

    ConcurrentHashMap<String, Adventure> adventureStorageMap;

    public void initialize(AdvMainDatabase dB)
    {
        mainDatabase = dB;
        initializeButtonToolBar();
        adventureStorageMap = new ConcurrentHashMap<>();
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
        profile1.setOnMousePressed(mouseEvent -> startAdventure(AdventureConstants.PROFILE_1));
        profile2.setOnMousePressed(mouseEvent -> startAdventure(AdventureConstants.PROFILE_2));
        profile3.setOnMousePressed(mouseEvent -> startAdventure(AdventureConstants.PROFILE_3));

    }

    private void validateProfiles() {
        checkProfile(AdventureConstants.PROFILE_1, profile1, 1);
        checkProfile(AdventureConstants.PROFILE_2, profile2, 2);
        checkProfile(AdventureConstants.PROFILE_3, profile3, 3);
    }

    public void checkProfile(String profile, ProfileNode proNode, int num) {
        AdventureDatabase adventureDatabase = new AdventureDatabase(mainDatabase);
        Adventure adventure = new Adventure(mainDatabase, adventureDatabase, profile);
        adventureStorageMap.put(profile, adventure);
        String name = adventure.getProfileName();
        if(name == null) {
            adventure.setNewProfile(true);
            proNode.initialize(EMPTY_PROFILE,num+"", profile);
        }
        else {
            proNode.initialize(mainDatabase, adventure, num+"", profile, this);
        }
    }

    private Adventure selectAdventure(String profile)
    {
        return adventureStorageMap.get(profile);
    }

    private void startAdventure(String profile) {
        Adventure adventure = selectAdventure(profile);

        if(adventure.isNewProfile())
        {
            AdvNewProfileOptionsPane profileOptionsPane = new AdvNewProfileOptionsPane();
            profileOptionsPane.initialize(mainDatabase, adventure, advStartPane);
            changeScene(profileOptionsPane);
        }
        else
        {
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
