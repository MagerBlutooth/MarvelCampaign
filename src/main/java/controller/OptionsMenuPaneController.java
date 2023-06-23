package controller;

import javafx.scene.Scene;
import view.pane.MainMenuPane;

public class OptionsMenuPaneController extends CampaignBasePaneController {

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }

    @Override
    public void initialize(ControllerDatabase database) {
        super.initialize(database);
    }

    @Override
    public void initializeButtonToolBar() {
        MainMenuPane mainMenuPane = new MainMenuPane();
        mainMenuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }


}
