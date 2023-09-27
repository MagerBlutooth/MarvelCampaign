package adventure.controller;

import adventure.model.AdvControllerDatabase;
import adventure.view.pane.AdvEditorMenuPane;
import adventure.view.pane.AdvStartPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import campaign.model.database.MasterThingDatabase;

public class AdvMainMenuController extends AdvPaneController {

    @FXML
    StackPane mainPane;
    @FXML
    Button startButton;
    @FXML
    Button configurationButton;
    @FXML
    Button exitButton;

    public void startAdventure() {

        AdvStartPane advStartPane = new AdvStartPane();
        advStartPane.initialize(controllerDatabase);
        changeScene(advStartPane);
    }

    public void configureAdventure() {
        AdvEditorMenuPane editorMenuPane = new AdvEditorMenuPane();
        editorMenuPane.initialize(controllerDatabase);
        changeScene(editorMenuPane);
    }

    public void exit() {
        Platform.exit();
    }

    @Override
    public Scene getCurrentScene() {
        return mainPane.getScene();
    }


    public void initialize(MasterThingDatabase masterThingDatabase)
    {
        controllerDatabase = new AdvControllerDatabase(masterThingDatabase);
    }
}
