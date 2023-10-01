package snapMain.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import snapMain.model.database.MasterThingDatabase;
import snapMain.view.pane.CampaignStartPane;
import snapMain.view.pane.EditorMenuPane;
import snapMain.view.pane.LoadSelectPane;
import snapMain.view.pane.OptionsMenuPane;

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
        campaignStartPane.initialize(mainDatabase);
        changeScene(campaignStartPane);
    }

    public void loadCampaign() {
        LoadSelectPane loadPane = new LoadSelectPane();
        loadPane.initialize(mainDatabase);
        changeScene(loadPane);
    }

    public void campaignEdit() {
        EditorMenuPane editorMenuPane = new EditorMenuPane();
        editorMenuPane.initialize(mainDatabase);
        changeScene(editorMenuPane);
    }

    public void editOptions() {
        OptionsMenuPane optionsMenuPane = new OptionsMenuPane();
        optionsMenuPane.initialize(mainDatabase);
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
        mainDatabase = new MainDatabase(masterThingDatabase);
    }
}
