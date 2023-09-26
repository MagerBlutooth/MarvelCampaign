package campaign.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import campaign.model.database.MasterThingDatabase;
import campaign.view.pane.CampaignStartPane;
import campaign.view.pane.EditorMenuPane;
import campaign.view.pane.LoadSelectPane;
import campaign.view.pane.OptionsMenuPane;

public class MainMenuController extends BasePaneController {

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
