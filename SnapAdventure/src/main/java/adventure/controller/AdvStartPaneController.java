package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.node.ProfileNode;
import adventure.view.pane.*;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.pane.FullViewPane;

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

        final FullViewPane[] newPane = new FullViewPane[1];
        Task<FullViewPane> task = new Task<>() {
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
        new Thread(task).start();
    }

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }
}
