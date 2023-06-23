package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import model.database.MasterThingDatabase;
import view.pane.PlayerLoadPane;

public class PlayerMainMenuController extends CampaignPaneController {

    @FXML
    StackPane mainPane;
    @FXML
    Button loadButton;
    @FXML
    Button exitButton;

    public void loadCampaign() {
        PlayerLoadPane loadPane = new PlayerLoadPane();
        loadPane.initialize(controllerDatabase);
        changeScene(loadPane);
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
        controllerDatabase = new ControllerDatabase(masterThingDatabase);
    }
}
