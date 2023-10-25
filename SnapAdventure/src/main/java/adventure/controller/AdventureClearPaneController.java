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
    Label victoryLabel;
    @FXML
    CardView enemyView;

    Adventure adventure;

    public void initialize(AdvMainDatabase database, Adventure a)
    {
        mainDatabase = database;
        adventure = a;
        Enemy finalBoss = a.getFinalBoss();
        a.saveAdventure();
        if(finalBoss.getCurrentHP() > 0) {
            victoryLabel.setText("Congratulations! You have stopped the mastermind " + finalBoss +
                    " from retrieving the Infinity Stones. Perhaps if you had obtained all of them, you may have been "
                    + "able" + "to confront them directly. They escaped back into the multiverse and your world is " +
                    "safe...for now.");

        }
        else{
            victoryLabel.setText("Oh, snap! You have defeated the evil mastermind " + finalBoss +
                    " using the combined power of the Infinity Stones and put an end to their reign of terror. " +
                    "You have" + "saved not only your universe, but the entire multiverse from this great evil.");
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
