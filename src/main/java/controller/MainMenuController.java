package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import model.database.MasterThingDatabase;
import view.pane.CampaignStartPane;
import view.pane.EditorMenuPane;
import view.pane.LoadSelectPane;
import view.pane.OptionsMenuPane;

public class MainMenuController extends CampaignPaneController {

    @FXML
    StackPane mainPane;
    @FXML
    Button campaignEditButton;
    @FXML
    Button startButton;
    @FXML
    Button loadButton;
    //@FXML
    //Button optionsButton;
    @FXML
    Button exitButton;
    public void startCampaign() {

        CampaignStartPane campaignStartPane = new CampaignStartPane();
        campaignStartPane.initialize(controllerDatabase);
        changeScene(campaignStartPane);
    }

    public void loadCampaign() {
        LoadSelectPane loadPane = new LoadSelectPane();
        loadPane.initialize(controllerDatabase);
        changeScene(loadPane);
    }

    public void campaignEdit() {
        EditorMenuPane editorMenuPane = new EditorMenuPane();
        editorMenuPane.initialize(controllerDatabase);
        changeScene(editorMenuPane);
    }

    public void editOptions() {
        OptionsMenuPane optionsMenuPane = new OptionsMenuPane();
        optionsMenuPane.initialize(controllerDatabase);
        changeScene(new OptionsMenuPane());
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
