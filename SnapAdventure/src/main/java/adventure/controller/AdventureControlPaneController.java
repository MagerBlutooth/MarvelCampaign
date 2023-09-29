package adventure.controller;

import adventure.model.AdvControllerDatabase;
import adventure.model.World;
import adventure.model.adventure.Adventure;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.AdventureSaver;
import adventure.view.node.AdventureActionNode;
import adventure.view.node.TeamDisplayNode;
import adventure.view.node.WorldDisplayNode;
import adventure.view.pane.AdvMainMenuPane;
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
    @FXML
    AdventureActionNode adventureActionNode;
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
        int num = adventure.getCurrentWorldNum();
        worldDisplayNode.initialize(database,world, adventure.getCurrentWorldNum());
        adventure.saveAdventure();
    }

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }
}
