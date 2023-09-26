package adventure.controller;

import adventure.model.AdvControllerDatabase;
import adventure.model.Adventure;
import adventure.model.AdventureDatabase;
import adventure.view.pane.AdvMainMenuPane;
import adventure.view.pane.AdvStartPane;
import campaign.controller.ButtonToolBarPaneController;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class AdventureControlPaneController extends ButtonToolBarPaneController<AdvControllerDatabase> {

    @FXML
    AdvStartPane startPane;
    Adventure adventure;

    @Override
    public void initializeButtonToolBar() {
        AdvMainMenuPane mainMenuPane = new AdvMainMenuPane();
        mainMenuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    public void initialize(AdvControllerDatabase database, Adventure adventure)
    {
        super.initialize(database);

        AdventureDatabase adventureDatabase = new AdventureDatabase(database);
        adventure = new Adventure(adventureDatabase);
    }

    @Override
    public Scene getCurrentScene() {
        return startPane.getScene();
    }
}
