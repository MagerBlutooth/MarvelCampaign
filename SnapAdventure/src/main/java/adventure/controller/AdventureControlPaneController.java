package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.World;
import adventure.model.adventure.Adventure;
import adventure.model.AdventureDatabase;
import adventure.model.thing.Section;
import adventure.view.node.AdventureActionNode;
import adventure.view.node.TeamDisplayNode;
import adventure.view.node.WorldDisplayNode;
import adventure.view.pane.AdvMainMenuPane;
import adventure.view.pane.AdventureControlPane;
import javafx.scene.control.Button;
import snapMain.view.button.ButtonToolBar;
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
    @FXML
    AdventureControlPane adventureControlPane;
    Adventure adventure;
    AdventureDatabase adventureDatabase;


    public void initialize(AdvMainDatabase database, Adventure a)
    {
        super.initialize(database);
        initializeButtonToolBar();
        adventureDatabase = new AdventureDatabase(database);
        adventure = a;
        teamDisplayNode.initialize(database, a.getTeam(), a);
        worldDisplayNode.initialize(database,a.getCurrentWorld(), adventure.getCurrentWorldNum(), adventureControlPane);
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

    public Adventure getAdventure() {
        return adventure;
    }

    public void skipSection(Section section) {
        worldDisplayNode.revealNextSection(section.getSectionNum());
    }

    public void refreshToMatch() {
        teamDisplayNode.refresh();
        worldDisplayNode.refresh(adventure.getCurrentWorld());
    }

    public void completeSection() {
        adventure.completeCurrentSection();
        worldDisplayNode.refresh(adventure.getCurrentWorld());
    }

    public void completeWorld() {
        adventure.completeCurrentWorld();
        worldDisplayNode.refresh(adventure.getCurrentWorld());
    }
}
