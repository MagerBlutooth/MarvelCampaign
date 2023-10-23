package adventure.view.node;

import adventure.model.AdvMainDatabase;
import adventure.model.stats.CardStats;
import adventure.model.stats.AdvMatchResult;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.thing.CardView;

public class CardStatEntryNode extends StackPane {

    @FXML
    CardView cardView;
    @FXML
    Label rankLabel;
    @FXML
    Label matchLabel;
    @FXML
    Label winLabel;
    @FXML
    Label lossLabel;
    @FXML
    Label escapeLabel;
    @FXML
    Label forceRetreatLabel;

    public CardStatEntryNode() {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("cardStatEntryNode.fxml", this, this);
    }

    public void initialize(AdvMainDatabase db, int rank, int id, CardStats stats) {

        TargetDatabase<Card> cards = db.lookupDatabase(TargetType.CARD);
        Card c = cards.lookup(id);
        cardView.initialize(db, c, ViewSize.SMALL, false);
        rankLabel.setText(rank + "");
        matchLabel.setText(stats.getTotalMatches()+"");
        winLabel.setText(stats.lookupStat(AdvMatchResult.WIN) + "");
        lossLabel.setText(stats.lookupStat(AdvMatchResult.LOSE) + "");
        escapeLabel.setText(stats.lookupStat(AdvMatchResult.ESCAPE) + "");
        forceRetreatLabel.setText(stats.lookupStat(AdvMatchResult.FORCE_RETREAT) + "");
    }

}
