package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.thing.AdvCard;
import adventure.model.thing.Enemy;
import adventure.view.node.EnemyControlNode;
import adventure.view.node.HPDisplayNode;
import adventure.view.pane.AdventureControlPane;
import adventure.view.pane.WorldClearPane;
import adventure.view.popup.HPDialog;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;

import java.util.Optional;

public class BossViewPaneController extends SectionViewPaneController {

    public void defeatBoss()
    {
        WorldClearPane worldClearPane = new WorldClearPane();
        worldClearPane.initialize(mainDatabase, adventure, controlPane);
        changeScene(worldClearPane);
    }

    @FXML
    @Override
    public void completeSection()
    {
        section.complete();
        adventure.collectPickups(section);
        defeatBoss();

    }

}
