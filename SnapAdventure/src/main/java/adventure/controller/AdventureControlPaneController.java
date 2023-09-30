package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.World;
import adventure.model.adventure.Adventure;
import adventure.model.AdventureDatabase;
import adventure.view.node.AdventureActionNode;
import adventure.view.node.TeamDisplayNode;
import adventure.view.node.WorldDisplayNode;
import adventure.view.pane.AdvMainMenuPane;
import campaign.model.thing.Card;
import campaign.view.button.ButtonToolBar;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.util.List;

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


    public void initialize(AdvMainDatabase database, Adventure a)
    {
        super.initialize(database);
        initializeButtonToolBar();
        adventureDatabase = new AdventureDatabase(database);
        adventure = a;
        World world = a.getCurrentWorld();
        teamDisplayNode.initialize(database, a.getTeam());
        worldDisplayNode.initialize(database,world, adventure.getCurrentWorldNum(), adventure.getCurrentSectionNum());
        adventureActionNode.initialize(database, adventure);
        adventure.saveAdventure();
    }

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        AdvMainMenuPane mainMenuPane = new AdvMainMenuPane();
        mainMenuPane.initialize(mainDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }


}
