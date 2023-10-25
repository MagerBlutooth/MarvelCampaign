package adventure.view.popup;

import adventure.controller.dialog.CardSearchSelectDialogController;
import adventure.controller.dialog.CardStatDisplayPopupController;
import adventure.model.AdvMainDatabase;
import adventure.model.stats.CardStats;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.Button;
import snapMain.model.target.Card;

import java.util.Map;

public class CardStatDisplayPopup extends AdvDialog<Card>{

    CardStatDisplayPopupController controller;
    public CardStatDisplayPopup()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("cardStatDisplayPopup.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase mainDatabase, Map<Integer, CardStats> cardStatMap)
    {
        controller.initialize(mainDatabase, cardStatMap);
    }
}
