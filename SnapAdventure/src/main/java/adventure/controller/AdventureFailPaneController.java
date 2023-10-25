package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.stats.CardStats;
import adventure.model.target.Enemy;
import adventure.model.target.base.AdvCard;
import adventure.view.pane.AdvMainMenuPane;
import adventure.view.popup.CardStatDisplayPopup;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import snapMain.view.ViewSize;
import snapMain.view.thing.CardView;

import java.util.Map;

public class AdventureFailPaneController extends FullViewPaneController {

    @FXML
    Label failureLabel;

    @FXML
    CardView enemyView;

    Adventure adventure;
    public void initialize(AdvMainDatabase database, Adventure a)
    {
        mainDatabase = database;
        adventure = a;
        Enemy finalBoss = a.getFinalBoss();
        a.saveAdventure();
        failureLabel.setText("You have failed. Your team was no match for the diabolical mastermind " + finalBoss +
                ". Your team has been defeated, and the Infinity Stones have been taken. The world is now doomed.");
        enemyView.initialize(database, ((AdvCard)finalBoss.getSubject()).getCard(), ViewSize.LARGE, false);
    }

    @Override
    public Scene getCurrentScene() {
        return failureLabel.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
    }

    @FXML
    public void showCardStats()
    {
        Map<Integer, CardStats> sortedCardStats = adventure.getRankedCardStats();
        CardStatDisplayPopup popup  = new CardStatDisplayPopup();
        popup.initialize(mainDatabase, sortedCardStats);
        popup.showAndWait();
    }

    @FXML
    public void endGame()
    {
        AdvMainMenuPane mainMenuPane = new AdvMainMenuPane();
        mainMenuPane.initialize(mainDatabase);
        changeScene(mainMenuPane);
    }

}
