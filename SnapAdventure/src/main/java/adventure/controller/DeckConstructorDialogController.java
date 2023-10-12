package adventure.controller;

import adventure.model.stats.MatchResult;
import adventure.view.node.DeckItemControlNode;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.*;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.helper.DeckCodeConverter;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.manager.CardManager;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

import java.util.ArrayList;

public class DeckConstructorDialogController implements GridActionController<Card>  {

    @FXML
    CardManager cardChoices;
    @FXML
    GridDisplayNode<Card> deckDisplay;
    DeckGridController deckGridController;
    MainDatabase mainDatabase;
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

    public void initialize(MainDatabase db, CardList selectableCards)
    {
        mainDatabase = db;
        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(winButton, loseButton, escapeButton, forceRetreatButton);
        toggleGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        winButton.setSelected(true);
        deckGridController = new DeckGridController();
        deckGridController.initialize(db, deckDisplay, this);
        cardChoices.initialize(selectableCards, TargetType.CARD, this, ViewSize.TINY, false);
        cardChoices.setPrefColumns(8);
        deckDisplay.setMaxWidth(700);
        deckDisplay.initialize(new CardList(new ArrayList<>()), TargetType.CARD, deckGridController, ViewSize.SMALL,
                false);
    }

    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        DeckItemControlNode node = new DeckItemControlNode();
        node.initialize(mainDatabase, card, deckGridController.getDeck(), v);
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

    public CardList getDeck() {
        return deckGridController.getDeck();
    }

    public MatchResult getMatchResult() {
        return result;
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
        codeConverter.convertDeckCodeToDeck(mainDatabase.lookupDatabase(TargetType.CARD), data);
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
}
