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

public class AdventureClearPaneController extends FullViewPaneController {


    @FXML
    Label totalPlayTime;
    @FXML
    Label victoryLabel;
    @FXML
    CardView enemyView;
    @FXML
    CardView trueEnemyView;

    Adventure adventure;

    public void initialize(AdvMainDatabase database, Adventure a)
    {
        mainDatabase = database;
        adventure = a;
        totalPlayTime.setText("Total Play Time: " + a.getTotalPlayTime());
        Enemy finalBoss = a.getFinalBoss();
        a.saveAdventure();
        int currentWorldNum = a.getCurrentWorldNum();
        if(currentWorldNum == a.getNumberOfWorlds()) {
            Enemy trueFinalBoss = a.getTrueFinalBoss();
            enemyView.lowlight();
            trueEnemyView.initialize(mainDatabase, trueFinalBoss.getObtainableCard().getCard(), ViewSize.LARGE, false);
            victoryLabel.setText("Congratulations! You have stopped the mastermind " + finalBoss +
                    " from retrieving the Infinity Stones. However, it turns out "+ finalBoss + " was only a puppet. " +
                    "The true mastermind " + trueFinalBoss + " has escaped into the multiverse. Perhaps if you had " +
                    "collected all the Infinity Stones, you could have confronted this diabolical genius directly. " +
                    "At the very least, the world is safe...for now.");
        }
        else{
            victoryLabel.setText("Oh, snap! You have defeated the evil mastermind " + finalBoss +
                    " using the combined power of the Infinity Stones and put an end to their reign of terror. " +
                    " You have saved not only your universe, but the entire multiverse from this great evil.");
        }
        enemyView.initialize(database, ((AdvCard) finalBoss.getSubject()).getCard(), ViewSize.LARGE, false);
    }

    @Override
    public Scene getCurrentScene() {
        return victoryLabel.getScene();
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


    public void endGame()
    {
        AdvMainMenuPane mainMenuPane = new AdvMainMenuPane();
        mainMenuPane.initialize(mainDatabase);
        changeScene(mainMenuPane);
    }
}
