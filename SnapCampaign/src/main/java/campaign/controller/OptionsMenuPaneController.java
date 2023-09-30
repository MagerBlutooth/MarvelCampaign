package campaign.controller;

import javafx.scene.Scene;
import campaign.view.pane.MainMenuPane;

public class OptionsMenuPaneController extends ButtonToolBarPaneController {

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }

    @Override
    public void initialize(MainDatabase database) {
        super.initialize(database);
    }

    @Override
    public void initializeButtonToolBar() {
        MainMenuPane mainMenuPane = new MainMenuPane();
        mainMenuPane.initialize(mainDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }


}
