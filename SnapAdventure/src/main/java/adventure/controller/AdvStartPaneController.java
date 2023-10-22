package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.adventure.Adventure;
import adventure.view.node.ProfileNode;
import adventure.view.pane.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import snapMain.model.logger.MLogger;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.pane.FullViewPane;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.FileHandler;

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

    public void validateProfiles() {
        checkProfile(AdventureConstants.PROFILE_1, profile1, 1);
        checkProfile(AdventureConstants.PROFILE_2, profile2, 2);
        checkProfile(AdventureConstants.PROFILE_3, profile3, 3);
    }

    public void checkProfile(String profile, ProfileNode proNode, int num) {
        Adventure adventure = new Adventure(mainDatabase, profile);
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
        buttonToolBar.setDisable(true);
        final FullViewPane[] newPane = new FullViewPane[1];

        if(!adventure.isNewProfile() && adventure.failStateCheck())
        {
            AdventureFailPane adventureFailPane = new AdventureFailPane();
            adventureFailPane.initialize(mainDatabase, adventure);
            changeScene(adventureFailPane);
            return;
        }
        Task<FullViewPane> task = new Task<>() {
            @Override
            public boolean isCancelled() {
                return super.isCancelled();
            }

            @Override
            public FullViewPane call() {
                if(adventure.isNewProfile())
                {
                    AdvNewProfileOptionsPane profileOptionsPane = new AdvNewProfileOptionsPane();
                    profileOptionsPane.initialize(mainDatabase, adventure, advStartPane);
                    newPane[0] = profileOptionsPane;
                }
                else
                {
                    AdventureControlPane adventureControlPane = new AdventureControlPane();
                    adventureControlPane.initialize(mainDatabase, adventure);
                    newPane[0] = adventureControlPane;
                }
                return newPane[0];
            }

        };
        getCurrentScene().setCursor(Cursor.WAIT);
        task.setOnSucceeded(t -> {
            newPane[0] = task.getValue();
            changeScene(newPane[0]);
            newPane[0].setCursor(Cursor.DEFAULT);
        });
        task.setOnFailed(t -> {
            System.out.println("Help");
            if(task.isRunning())
                task.cancel();
        });
        new Thread(task).start();
    }

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }
}
