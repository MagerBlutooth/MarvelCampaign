package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.pane.AdventureControlPane;
import adventure.view.popup.CardDisplayPopup;
import adventure.view.popup.CardSearchSelectDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetList;

import java.util.Optional;

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
    public void generateCard()
    {
        controlPane.generateCard();

    }

    @FXML
    public void searchCard()
    {
        controlPane.searchCard();
    }
    @FXML
    public void randomCard()
    {
        AdventureDatabase database = adventure.getAdventureDatabase();
        Card card = database.getCards().getRandom();
        CardDisplayPopup popup = new CardDisplayPopup(mainDatabase, card,
                    randomCard.localToScreen(0.0,0.0));
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
