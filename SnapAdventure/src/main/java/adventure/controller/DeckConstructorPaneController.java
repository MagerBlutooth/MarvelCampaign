package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.stats.MatchResult;
import adventure.view.node.DeckItemControlNode;
import adventure.view.sortFilter.DeckLinkedFilterMenuButton;
import adventure.view.sortFilter.DeckLinkedSortMenuButton;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import snapMain.controller.grid.GridActionController;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.helper.DeckCodeConverter;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.manager.CardManager;
import snapMain.view.menu.FilterMenuButton;
import snapMain.view.menu.SortMenuButton;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;
import snapMain.view.pane.FullViewPane;

import java.util.ArrayList;

public class DeckConstructorPaneController extends AdvPaneController implements GridActionController<Card>  {


    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    Button confirmButton;
    @FXML
    CardManager cardChoices;
    @FXML
    GridDisplayNode<Card> deckDisplay;
    DeckGridController deckGridController;
    MatchResult result;
    ToggleGroup toggleGroup;
    @FXML
    ToggleButton winButton;
    @FXML
    ToggleButton loseButton;
    @FXML
    ToggleButton escapeButton;
    @FXML
    ToggleButton forceRetreatButton;
    @FXML
    DeckLinkedSortMenuButton sortButton;
    @FXML
    DeckLinkedFilterMenuButton filterButton;
    Adventure adventure;
    FullViewPane backPane;

    public void initialize(AdvMainDatabase db, FullViewPane pane, Adventure a)
    {
        mainDatabase = db;
        adventure = a;
        backPane = pane;
        initializeButtonToolBar();
        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(winButton, loseButton, escapeButton, forceRetreatButton);
        toggleGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        CardList selectableCards = a.getActiveCards();
        winButton.setSelected(true);
        setWin();
        CardList verifiedRecentDeck = verifyCardList(adventure.getMostRecentDeck());
        deckGridController = new DeckGridController();
        deckGridController.initialize(db, deckDisplay, verifiedRecentDeck, this);
        cardChoices.initialize(selectableCards, TargetType.CARD, this, ViewSize.TINY, false);
        cardChoices.setPrefColumns(8);
        deckDisplay.setMaxWidth(700);
        deckDisplay.initialize(verifiedRecentDeck, TargetType.CARD, deckGridController, ViewSize.SMALL,
                false);
        deckDisplay.setBorder((new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        sortButton.initialize(cardChoices.getListNodeController(), deckGridController);
        filterButton.initialize(cardChoices.getListNodeController(), deckGridController);
        confirmButton.disableProperty().bind(Bindings.notEqual(SnapMainConstants.MAX_DECK_SIZE,
                deckGridController.getDeckSizeProperty()));
    }

    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        DeckItemControlNode node = new DeckItemControlNode();
        node.initialize(mainDatabase, card, v);
        setMouseEvents(node);
        return node;
    }

    @FXML
    public void copyToCode()
    {
        DeckCodeConverter codeConverter = new DeckCodeConverter();
        codeConverter.encodeDeckToClipboard(deckGridController.getDeck());
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

    public void toggleNodeLight(Card c) {
        cardChoices.toggleNodeLight(c);
    }

    @Override
    public void setMouseEvents(ControlNode<Card> node) {
            Card card = node.getSubject();
            node.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    boolean toggled = deckGridController.toggleEntry(card);
                    if(toggled)
                        node.toggleNodeLight();
                    e.consume();
                }});
        }
    @FXML
    public void confirmDeck()
    {
        adventure.setMostRecentDeck(deckGridController.getDeck());
        adventure.updateStats(deckGridController.getDeck(), result);
        changeScene(backPane);
    }

    public void setWin()
    {
        result = MatchResult.WIN;
    }

    public void setLose()
    {
        result = MatchResult.LOSE;
    }

    public void setForceRetreat()
    {
        result = MatchResult.FORCE_RETREAT;
    }

    public void setEscape()
    {
        result = MatchResult.ESCAPE;
    }

    public void pasteFromClipboard()
    {
        DeckCodeConverter codeConverter = new DeckCodeConverter();
        String data = (String) Clipboard.getSystemClipboard().getContent(DataFormat.PLAIN_TEXT);
        CardList pastedDeck = codeConverter.convertDeckCodeToDeck(mainDatabase.lookupDatabase(TargetType.CARD), data);
        CardList verifiedCards = verifyCardList(pastedDeck);
        if(!verifiedCards.isEmpty()) {
            clearDeck();
            for(Card c: pastedDeck) {
                deckGridController.toggleEntry(c);
            }
        }
    }

    public void clearDeck()
    {
        deckGridController.clear();
        deckDisplay.clear();
        highlightAll();
    }

    private void highlightAll() {
        cardChoices.highlightAll();
    }

    @Override
    public Scene getCurrentScene() {
        return deckDisplay.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        buttonToolBar.initialize(backPane);
    }
    private CardList verifyCardList(CardList candidateCards) {
        CardList validCards = new CardList(new ArrayList<>());
        for(Card c: candidateCards)
        {
            if(adventure.getActiveCards().contains(c))
                validCards.add(c);
        }
        return validCards;
    }

}
