package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.adventure.DeckProfileList;
import adventure.model.stats.MatchResult;
import adventure.view.node.DeckItemControlNode;
import adventure.view.popup.CardDisplayPopup;
import adventure.view.sortFilter.DeckLinkedFilterMenuButton;
import adventure.view.sortFilter.DeckLinkedSortMenuButton;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
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
import snapMain.view.grabber.IconConstant;
import snapMain.view.manager.CardManager;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;
import snapMain.view.pane.FullViewPane;

import java.util.ArrayList;

public class DeckConstructorPaneController extends AdvPaneController implements GridActionController<Card>  {

    @FXML
    ToggleButton deckProfile1;
    @FXML
    ToggleButton deckProfile2;
    @FXML
    ToggleButton deckProfile3;
    @FXML
    ToggleButton deckProfile4;
    ToggleGroup deckProfileToggle;
    @FXML
    Button copyButton;
    @FXML
    Button pasteButton;
    @FXML
    Button clearButton;
    @FXML
    Label deckButtonConfirmText;
    @FXML
    Button confirmButton;
    @FXML
    CardManager allSelectableCards;
    @FXML
    GridDisplayNode<Card> deckDisplay;
    DeckGridController deckGridController;
    MatchResult result;
    @FXML
    Button randomCardFromTeamButton;
    @FXML
    Button randomCardFromDeckButton;
    ToggleGroup matchResultToggle;
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
    DeckProfileList deckProfiles;
    int profileNum;

    public void initialize(AdvMainDatabase db, FullViewPane pane, Adventure a)
    {
        mainDatabase = db;
        adventure = a;
        backPane = pane;
        deckProfiles = verifyDeckProfiles(adventure.getDeckProfiles());
        matchResultToggle = new ToggleGroup();
        matchResultToggle.getToggles().addAll(winButton, loseButton, escapeButton, forceRetreatButton);
        matchResultToggle.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        deckProfileToggle = new ToggleGroup();
        deckProfileToggle.getToggles().addAll(deckProfile1, deckProfile2, deckProfile3, deckProfile4);
        deckProfileToggle.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        toggleDeckProfile();
        setButtonImages();
        CardList selectableCards = a.getActiveCards();
        winButton.setSelected(true);
        setWin();
        deckGridController = new DeckGridController();
        deckGridController.initialize(db, deckDisplay, deckProfiles.getLatestProfile(), this);
        allSelectableCards.initialize(selectableCards, TargetType.CARD, this, ViewSize.TINY, false);
        allSelectableCards.setPrefColumns(8);
        deckDisplay.setMaxWidth(700);
        deckDisplay.initialize(deckProfiles.getLatestProfile(), TargetType.CARD, deckGridController, ViewSize.SMALL,
                false);
        deckDisplay.setBorder((new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        sortButton.initialize(allSelectableCards.getListNodeController(), deckGridController);
        filterButton.initialize(allSelectableCards.getListNodeController(), deckGridController);
        confirmButton.disableProperty().bind(Bindings.notEqual(SnapMainConstants.MAX_DECK_SIZE,
                deckGridController.getDeckSizeProperty()));
        deckGridController.toggleNodeLights();
    }

    private void toggleDeckProfile() {
        switch(deckProfiles.getLatestProfileNum())
        {
            case 1:
                deckProfileToggle.selectToggle(deckProfile2);
                break;
            case 2:
                deckProfileToggle.selectToggle(deckProfile3);
                break;
            case 3:
                deckProfileToggle.selectToggle(deckProfile4);
                break;
            default:
                deckProfileToggle.selectToggle(deckProfile1);
        }

    }

    private void setButtonImages() {
        setGraphic(copyButton, new ImageView(mainDatabase.grabIcon(IconConstant.COPY)), false);
        setGraphic(pasteButton, new ImageView(mainDatabase.grabIcon(IconConstant.PASTE)), false);
        setGraphic(clearButton, new ImageView(mainDatabase.grabIcon(IconConstant.CLEAR)), false);
        setGraphic(randomCardFromDeckButton, new ImageView(mainDatabase.grabIcon(IconConstant.DICE)), true);
        setGraphic(randomCardFromTeamButton, new ImageView(mainDatabase.grabIcon(IconConstant.DICE)), true);
    }

    private void setGraphic(Button b, ImageView image, boolean flat)
    {
        image.setFitWidth(30);
        if(flat)
            image.setFitHeight(30);
       else
           image.setFitHeight(40);
       b.setOnMouseEntered(e -> {
           ColorAdjust lightUp = new ColorAdjust();
           lightUp.setSaturation(-1.0);
           lightUp.setHue(1.0);
           image.setEffect(lightUp);
       });
       b.setOnMouseExited(e -> image.setEffect(null));
        b.setGraphic(image);
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
        deckButtonConfirmText.setText("Deck Code Pasted to Clipboard");
    }

    @FXML
    public void randomCardFromTeam()
    {
        Card randomCard = adventure.getActiveCards().getRandom();
        CardDisplayPopup popup = new CardDisplayPopup(mainDatabase, randomCard,
                randomCardFromTeamButton.localToScreen(100.0,0.0));
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                popup.hide();
            }
        });
    }

    @FXML
    public void randomCardFromDeck()
    {
        CardList cards = deckGridController.getChosenCards();
        if(!cards.isEmpty()) {
            Card randomCard = deckGridController.getChosenCards().getRandom();
            CardDisplayPopup popup = new CardDisplayPopup(mainDatabase, randomCard,
                    randomCardFromDeckButton.localToScreen(100.0, 0.0));
            popup.show();
            popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (!isNowFocused) {
                    popup.hide();
                }
            });
        }
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
        allSelectableCards.toggleNodeLight(c);
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
        adventure.updateDeckProfiles(deckProfiles, getProfileNum());
        adventure.updateStats(deckGridController.getDeck(), result);
        changeScene(backPane);
    }

    private int getProfileNum() {
        return profileNum;
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
        CardList verifiedCards = verifyDeck(pastedDeck);
        if(!verifiedCards.isEmpty()) {
            clearDeck();
            for(Card c: pastedDeck) {
                deckGridController.toggleEntry(c);
                toggleNodeLight(c);
            }
            deckButtonConfirmText.setText("Deck Pasted from Clipboard");
        }
        else{
            deckButtonConfirmText.setText("No valid cards found to paste");
        }
    }

    public void clearDeck()
    {
        deckGridController.clear();
        deckDisplay.clear();
        highlightAll();
        deckButtonConfirmText.setText("Deck Cleared");
    }

    private void highlightAll() {
        allSelectableCards.highlightAll();
    }

    @Override
    public Scene getCurrentScene() {
        return deckDisplay.getScene();
    }

    @Override
    public void initializeButtonToolBar() {

    }

    private CardList verifyDeck(CardList deck)
    {
        CardList validCards = new CardList(new ArrayList<>());
        for(Card c: deck)
        {
            if(adventure.getActiveCards().contains(c))
                validCards.add(c);
        }
        return validCards;
    }

    private DeckProfileList verifyDeckProfiles(DeckProfileList profileList) {
        DeckProfileList newProfileList = new DeckProfileList(profileList.size());
        for(int i = 0; i < profileList.size(); i++)
        {
            newProfileList.setProfile(i, profileList.getProfile(i));
        }
        return newProfileList;
    }

    public void goBack()
    {
        changeScene(backPane);
    }

    public void switchProfile(int pNum)
    {
        saveDeckProfile();
        clearDeck();
        for(Card c: deckProfiles.getProfile(pNum)) {
            deckGridController.toggleEntry(c);
            toggleNodeLight(c);
        }
        profileNum = pNum;
        int proFileNumDisplay = pNum+1;
        deckButtonConfirmText.setText("Switched to Profile " + proFileNumDisplay);
    }

    public void switchProfile1()
    {
        switchProfile(0);

    }
    public void switchProfile2()
    {
        switchProfile(1);
    }

    public void switchProfile3()
    {
        switchProfile(2);
    }
    public void switchProfile4()
    {
        switchProfile(3);
    }

    public void saveDeckProfile() {
        deckProfiles.setProfile(profileNum, deckGridController.getDeck());
    }
}
