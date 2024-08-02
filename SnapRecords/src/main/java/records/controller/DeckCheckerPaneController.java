package records.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import records.model.DeckCheckResult;
import records.model.HallOfFameEntry;
import records.view.HallOfFameManagerPane;
import snapMain.controller.ButtonToolBarPaneController;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.database.TargetDatabase;
import snapMain.model.helper.DeckCodeConverter;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;

import java.util.LinkedHashSet;


public class DeckCheckerPaneController extends ButtonToolBarPaneController<MainDatabase> {

    @FXML
    Text uniqueCardsText;
    @FXML
    Label resultText;
    @FXML
    Text invalidCardsText;
    @FXML
    Button checkButton;
    @FXML
    GridDisplayNode<Card> deckDisplay;
    DeckCheckGridActionController displayController;

    HallOfFameEntry checkEntry;
    TargetDatabase<HallOfFameEntry> otherEntries;

    @Override
    public void initializeButtonToolBar() {

        HallOfFameManagerPane menuPane = new HallOfFameManagerPane();
        menuPane.initialize(mainDatabase);
        buttonToolBar.initialize(menuPane);
    }

    @Override
    public Scene getCurrentScene() {
        return checkButton.getScene();
    }

    public void initialize(MainDatabase m, TargetDatabase<HallOfFameEntry> otherHOF) {
        mainDatabase = m;
        checkEntry = new HallOfFameEntry();
        displayController = new DeckCheckGridActionController();
        displayController.initialize(mainDatabase);
        deckDisplay.initialize(checkEntry.getCards(), TargetType.CARD, displayController, ViewSize.SMALL,
                false);
        deckDisplay.setPrefColumns(6);
        otherEntries = otherHOF;
        initializeButtonToolBar();
    }

    @FXML
    private void checkDeckFromClipboard() {
        DeckCodeConverter codeConverter = new DeckCodeConverter();
        CardList newDeck = codeConverter.
                convertDeckCodeFromClipboardToDeck(getDatabase().lookupDatabase(TargetType.CARD));
        DeckCheckResult checkResult = verifyDeck(newDeck);
        if (newDeck.isEmpty())
            resultText.setText("Pasted Deck is Empty");
        else if (checkResult.getResult())
        {
            resultText.setText("Valid Deck");
            invalidCardsText.setText("");
        }
        else {
            resultText.setText("Invalid Deck.");
            invalidCardsText.setText("Shared Cards: " + checkResult.getInvalidCards().toString());
        }
        uniqueCardsText.setText("Unique Cards: " + checkResult.getUniqueCardCount());
        deckDisplay.initialize(newDeck, TargetType.CARD, displayController, ViewSize.SMALL, true);
        displayController.setInvalidCards(checkResult.getInvalidCards());
    }

    private DeckCheckResult verifyDeck(CardList deck) {
        checkEntry = new HallOfFameEntry();
        DeckCheckResult finalResult = new DeckCheckResult(true, 0);
        LinkedHashSet<Card> invalidCards = new LinkedHashSet<>();
        for(Card c: deck)
        {
          DeckCheckResult result = checkEntry.addCard(c, otherEntries);
            if(!result.getResult()) {
                finalResult.setResult(false);
                invalidCards.addAll(result.getInvalidCards().getCards());
            }
        }
        checkEntry.checkCardCountProperties(otherEntries);
        finalResult.setUniqueCardCount(checkEntry.getUniqueCardCountProperty().get());
        if(checkEntry.notEnoughUniqueCards())
            finalResult.setResult(false);
        finalResult.setInvalidCards(invalidCards.stream().toList());
        return finalResult;
    }
}
