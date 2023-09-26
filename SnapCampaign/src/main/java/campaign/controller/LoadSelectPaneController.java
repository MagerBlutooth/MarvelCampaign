package campaign.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import campaign.view.pane.MainMenuPane;
import campaign.view.pane.PlayerLoadPane;
import campaign.view.pane.WatcherLoadPane;

public class LoadSelectPaneController extends ButtonToolBarPaneController {

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
