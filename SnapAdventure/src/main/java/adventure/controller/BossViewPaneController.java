package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.thing.AdvCard;
import adventure.model.thing.Boss;
import adventure.view.node.BossControlNode;
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
    BossControlNode bossView;
    @FXML
    Label effectText;
    AdvMainDatabase database;
    AdventureControlPane controlPane;
    Boss boss;
    Adventure adventure;
    public void initialize(AdvMainDatabase dB, AdventureControlPane cP, Boss b) {
        database = dB;
        controlPane = cP;
        boss = b;
        adventure = cP.getAdventure();
        AdvCard card = b.getCard();
        bossView.initialize(database, b, database.grabImage(card), ViewSize.LARGE, b.isRevealed());
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
