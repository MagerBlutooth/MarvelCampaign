package adventure.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.helper.DeckCodeConverter;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;
import snapMain.view.pane.manager.CardManagerPane;

import java.util.ArrayList;

public class DeckConstructorDialogController implements GridActionController<Card>  {

    @FXML
    CardManagerPane cardChoices;
    @FXML
    GridDisplayNode<Card> deckDisplay;
    CardList deck;

    DeckGridController deckController;
    MainDatabase mainDatabase;

    public void initialize(MainDatabase db, CardList selectableCards)
    {
        mainDatabase = db;
        cardChoices.initialize(db, selectableCards);
        deckDisplay.initialize(selectableCards, TargetType.CARD, this, ViewSize.MEDIUM,
                false);
        deckController.initialize(db, deckDisplay);
        deck = new CardList(new ArrayList<>());
    }

    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(mainDatabase, card, i, v, blind);
        setMouseEvents(node);
        return node;
    }

    @FXML
    public void copyToCode()
    {
        DeckCodeConverter codeConverter = new DeckCodeConverter();
        codeConverter.encodeDeckToClipboard("Deck", deckController.getDeck());
    }

    @Override
    public MainDatabase getDatabase() {
        return mainDatabase;
    }

    @Override
    public void saveGridNode(ControlNode<Card> node) {

    }

    @Override
    public void createTooltip(ControlNode<Card> n) {

    }

    @Override
    public void createContextMenu(ControlNode<Card> n) {

    }

    @Override
    public void setMouseEvents(ControlNode<Card> node) {
            Card card = node.getSubject();
            node.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    deckController.toggleEntry(card);
                    e.consume();
                }});
        }

}
