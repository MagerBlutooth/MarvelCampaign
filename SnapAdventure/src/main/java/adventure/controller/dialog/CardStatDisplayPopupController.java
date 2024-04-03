package adventure.controller.dialog;

import adventure.model.AdvMainDatabase;
import adventure.model.stats.CardStats;
import adventure.view.node.CardStatDisplayNode;
import javafx.fxml.FXML;

import java.util.Map;

public class CardStatDisplayPopupController {

    @FXML
    CardStatDisplayNode cardRecordDisplayNode;
    public void initialize(AdvMainDatabase mainDatabase, Map<Integer, CardStats> cardStatMap) {
        cardRecordDisplayNode.initialize(mainDatabase, cardStatMap);
    }
}
