package adventure.controller;

import adventure.model.AdvControllerDatabase;
import adventure.model.Section;
import adventure.model.World;
import adventure.model.adventure.Adventure;
import adventure.model.AdventureDatabase;
import adventure.view.node.TeamDisplayNode;
import adventure.view.node.WorldDisplayNode;
import adventure.view.pane.AdvMainMenuPane;
import adventure.view.pane.AdvStartPane;
import campaign.controller.ButtonToolBarPaneController;
import campaign.view.button.ButtonToolBar;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class AdventureControlPaneController extends AdvPaneController {

    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    TeamDisplayNode teamDisplayNode;
    @FXML
    WorldDisplayNode worldDisplayNode;
    Adventure adventure;
    AdventureDatabase adventureDatabase;


    public void initialize(AdvControllerDatabase database, Adventure a)
    {
        super.initialize(database);
        AdvMainMenuPane mainMenuPane = new AdvMainMenuPane();
        buttonToolBar.initialize(mainMenuPane);
        adventureDatabase = new AdventureDatabase(database);
        adventure = a;
        World world = a.getCurrentWorld();
        teamDisplayNode.initialize(database, a.getTeam());
        worldDisplayNode.initialize(database,world);
    }

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }
}
