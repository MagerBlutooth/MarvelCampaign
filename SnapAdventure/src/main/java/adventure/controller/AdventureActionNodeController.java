package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.stats.CardStats;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.node.CardStatDisplayNode;
import adventure.view.node.CardStatEntryNode;
import adventure.view.pane.AdventureControlPane;
import adventure.view.popup.AdvDialog;
import adventure.view.popup.AdvPopup;
import adventure.view.popup.CardDisplayPopup;
import adventure.view.popup.CardStatDisplayPopup;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import snapMain.model.target.Card;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdventureActionNodeController {

    @FXML
    public Button randomCard;
    AdvMainDatabase mainDatabase;
    AdventureControlPane controlPane;

    @FXML
    public void draftCard()
    {
        controlPane.draftCard();
    }

    @FXML
    public void generateCards()
    {
        controlPane.generateCards();
    }

    @FXML
    public void searchCard()
    {
        controlPane.searchCard();
    }
    @FXML
    public void randomCard()
    {
        ActiveCardList cards = adventure.getActiveCards();
        ActiveCard card = cards.getRandom();
        CardDisplayPopup popup = new CardDisplayPopup(mainDatabase, card,
                    randomCard.localToScreen(100.0,50.0));
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                popup.hide();
            }
        });

    }

    Adventure adventure;

    public void initialize(AdvMainDatabase database, Adventure a, AdventureControlPane cPane) {
        mainDatabase = database;
        adventure = a;
        controlPane = cPane;
    }
}
