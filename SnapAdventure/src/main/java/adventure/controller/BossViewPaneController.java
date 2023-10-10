package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.thing.AdvCard;
import adventure.model.thing.Enemy;
import adventure.view.node.EnemyControlNode;
import adventure.view.node.HPDisplayNode;
import adventure.view.pane.AdventureControlPane;
import adventure.view.popup.HPDialog;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;

import java.util.Optional;

public class BossViewPaneController extends AdvPaneController {

    @FXML
    HPDisplayNode hpDisplay;
    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    EnemyControlNode bossView;
    @FXML
    Label effectText;
    AdvMainDatabase database;
    AdventureControlPane controlPane;
    Enemy boss;
    Adventure adventure;
    public void initialize(AdvMainDatabase dB, AdventureControlPane cP, Enemy b) {
        database = dB;
        controlPane = cP;
        boss = b;
        adventure = cP.getAdventure();
        bossView.initialize(database, b, database.grabImage(boss.getSubject()), ViewSize.LARGE, true);
        effectText.setText(b.getEffect());
        effectText.setMouseTransparent(true);
        effectText.setFocusTraversable(false);
        hpDisplay.initialize(b);
        initializeButtonToolBar();
    }

    @Override
    public Scene getCurrentScene() {
        return bossView.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        buttonToolBar.initialize(controlPane);
    }

    public void changeHP()
    {
        HPDialog dialog = new HPDialog();
        dialog.initialize(boss.getCurrentHP());
        Optional<Integer> newHP = dialog.showAndWait();
        newHP.ifPresent(integer -> boss.setCurrentHP(integer));
        hpDisplay.update();
    }


    public void defeatBoss()
    {
        controlPane.completeCurrentWorld();
        changeScene(controlPane);
    }

}
