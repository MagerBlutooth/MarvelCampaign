package adventure.view.node;

import adventure.controller.CardStatDisplayNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.stats.CardStats;
import adventure.model.target.ActiveCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.node.GridDisplayNode;

import java.util.Map;

public class CardStatDisplayNode extends GridDisplayNode<ActiveCard> {

    CardStatDisplayNodeController controller;

    public CardStatDisplayNode()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("cardStatDisplayNode.fxml", this);
        controller = adventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase db, Map<Integer, CardStats> cardStatsMap)
    {
        controller.initialize(db, cardStatsMap);
    }

}
