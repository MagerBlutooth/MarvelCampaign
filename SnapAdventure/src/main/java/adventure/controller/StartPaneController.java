package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdvProfile;
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

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

import static adventure.model.AdventureConstants.EMPTY_PROFILE;

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

    public void initialize(AdvMainDatabase dB)
    {
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

    private Adventure selectAdventure(ProfileNode proNode)
    {
        return proNode.getAdventure();
    }

    private void startAdventure(ProfileNode proNode) {
        Adventure adventure = selectAdventure(proNode);
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
