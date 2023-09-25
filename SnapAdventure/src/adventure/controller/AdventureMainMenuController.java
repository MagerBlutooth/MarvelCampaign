package adventure.controller;

import campaign.controller.ControllerDatabase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import campaign.model.database.MasterThingDatabase;
import campaign.view.pane.CampaignStartPane;
import campaign.view.pane.EditorMenuPane;
import campaign.view.pane.LoadSelectPane;

public class AdventureMainMenuController extends JourneyPaneController {

    @FXML
    StackPane mainPane;
    @FXML
    Button startButton;
    @FXML
    Button loadButton;
    @FXML
    Button configurationButton;
    @FXML
    Button exitButton;

    public void startJourney() {

        CampaignStartPane campaignStartPane = new CampaignStartPane();
        campaignStartPane.initialize(controllerDatabase);
        changeScene(campaignStartPane);
    }

    public void loadJourney() {
        LoadSelectPane loadPane = new LoadSelectPane();
        loadPane.initialize(controllerDatabase);
        changeScene(loadPane);
    }

    public void configureJourney() {
        EditorMenuPane editorMenuPane = new EditorMenuPane();
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
        controllerDatabase = new ControllerDatabase(masterThingDatabase);
    }
}
