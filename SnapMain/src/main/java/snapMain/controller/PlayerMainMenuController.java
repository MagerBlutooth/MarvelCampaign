package snapMain.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import snapMain.model.database.MasterThingDatabase;
import snapMain.view.pane.PlayerLoadPane;

public class PlayerMainMenuController extends BasePaneController {

    @FXML
    StackPane mainPane;
    @FXML
    Button loadButton;
    @FXML
    Button exitButton;

    public void loadCampaign() {
        PlayerLoadPane loadPane = new PlayerLoadPane();
        loadPane.initialize(mainDatabase);
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
        mainDatabase = new MainDatabase(masterThingDatabase);
    }
}
