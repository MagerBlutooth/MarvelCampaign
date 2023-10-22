package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.stats.CardStats;
import adventure.view.node.CardStatEntryNode;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import snapMain.controller.ScrollSetup;

import java.util.Map;

public class CardStatDisplayNodeController {

    @FXML
    TilePane groupList;
    AdvMainDatabase mainDatabase;

    public void initialize(AdvMainDatabase db, Map<Integer, CardStats> sortedCardStats)
    {
        mainDatabase = db;
        populateDisplay(sortedCardStats);
    }

    private void populateDisplay(Map<Integer, CardStats> cardStatsMap) {
        int rank = cardStatsMap.size();
        for(Map.Entry<Integer, CardStats> e: cardStatsMap.entrySet())
        {
            CardStatEntryNode node = new CardStatEntryNode();
            node.initialize(mainDatabase, rank, e.getKey(), e.getValue());
            groupList.getChildren().add(node);
            rank--;
        }
    }
}
