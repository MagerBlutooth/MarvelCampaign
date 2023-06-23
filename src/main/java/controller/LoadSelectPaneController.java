package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import view.pane.MainMenuPane;
import view.pane.PlayerLoadPane;
import view.pane.WatcherLoadPane;

public class LoadSelectPaneController extends CampaignBasePaneController {

    @FXML
    VBox mainBox;

    @Override
    public void initialize(ControllerDatabase database)
    {
        super.initialize(database);
    }

    public void loadPlayer()
    {
        PlayerLoadPane loadPane = new PlayerLoadPane();
        loadPane.initialize(controllerDatabase);
        changeScene(loadPane);

    }
    public void loadWatcher()
    {
        WatcherLoadPane loadPane = new WatcherLoadPane();
        loadPane.initialize(controllerDatabase);
        changeScene(loadPane);
    }

    @Override
    public Scene getCurrentScene() {
        return mainBox.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        MainMenuPane mainMenuPane = new MainMenuPane();
        mainMenuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }
}
