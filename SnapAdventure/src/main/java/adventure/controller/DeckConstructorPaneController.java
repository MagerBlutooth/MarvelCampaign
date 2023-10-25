package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.adventure.DeckProfileList;
import adventure.model.stats.AdvMatchResult;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.node.DeckItemControlNode;
import adventure.view.popup.*;
import adventure.view.sortFilter.DeckLinkedFilterMenuButton;
import adventure.view.sortFilter.DeckLinkedSortMenuButton;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import snapMain.controller.grid.GridActionController;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.helper.DeckCodeConverter;
import snapMain.model.logger.MLogger;
import snapMain.model.target.CardList;
import snapMain.model.target.StatusEffect;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.grabber.IconConstant;
import snapMain.view.manager.Manager;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;
import snapMain.view.pane.FullViewPane;

import java.util.ArrayList;
import java.util.Optional;

public class DeckConstructorPaneController extends FullViewPaneController implements GridActionController<ActiveCard> {

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
    Manager<ActiveCard> allSelectableCards;
    @FXML
    GridDisplayNode<ActiveCard> deckDisplay;
    DeckGridController deckGridController;
    @FXML
    Button randomCardFromTeamButton;
    @FXML
    Button randomCardFromDeckButton;
    @FXML
    DeckLinkedSortMenuButton sortButton;
    @FXML
    DeckLinkedFilterMenuButton filterButton;
    Adventure adventure;
    FullViewPane backPane;
    DeckProfileList deckProfiles;
    int profileNum;

    final static MLogger logger = new MLogger(AdventureControlPaneController.class);

    public void initialize(AdvMainDatabase db, FullViewPane pane, Adventure a) {
        mainDatabase = db;
        adventure = a;
        backPane = pane;
        deckProfiles = verifyDeckProfiles(adventure.getDeckProfiles());
        deckProfileToggle = new ToggleGroup();
        deckProfileToggle.getToggles().addAll(deckProfile1, deckProfile2, deckProfile3, deckProfile4);
        deckProfileToggle.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        toggleDeckProfile();
        setButtonImages();
        ActiveCardList selectableCards = a.getActiveCards();

        deckGridController = new DeckGridController();
        ActiveCardList verifiedDeck = verifyDeck(deckProfiles.getLatestProfile());
        deckGridController.initialize(db, deckDisplay, verifiedDeck, this);
        allSelectableCards.initialize(selectableCards, TargetType.CARD, this, ViewSize.TINY, true);
        allSelectableCards.setPrefColumns(8);
        deckDisplay.setMaxWidth(700);
        deckDisplay.initialize(verifiedDeck, TargetType.CARD, deckGridController, ViewSize.SMALL,
                true);
        deckDisplay.setBorder((new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        sortButton.initialize(allSelectableCards.getListNodeController(), deckGridController);
        filterButton.initialize(allSelectableCards.getListNodeController(), deckGridController);
        confirmButton.disableProperty().bind(Bindings.notEqual(SnapMainConstants.DECK_SIZE,
                deckGridController.getDeckSizeProperty()));
        deckGridController.toggleNodeLights();
    }

    private void toggleDeckProfile() {
        switch (deckProfiles.getLatestProfileNum()) {
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

    private void setGraphic(Button b, ImageView image, boolean flat) {
        image.setFitWidth(30);
        if (flat)
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
    public ControlNode<ActiveCard> createControlNode(ActiveCard card, IconImage i, ViewSize v, boolean statusVisible) {
        DeckItemControlNode node = new DeckItemControlNode();
        node.initialize(mainDatabase, card, i, v, true);
        setMouseEvents(node);
        return node;
    }

    @Override
    public ControlNode<ActiveCard> createEmptyNode(ViewSize v) {
        ControlNode<ActiveCard> cardNode = new ControlNode<>();
        cardNode.initialize(mainDatabase, new ActiveCard(), mainDatabase.grabBlankImage(TargetType.CARD),
                v, false);
        return cardNode;
    }

    @FXML
    public void copyToClipboard() {
        DeckCodeConverter codeConverter = new DeckCodeConverter();
        ActiveCardList cards = deckGridController.getDeck();
        CardList baseCards = cards.getBaseCards();
        codeConverter.encodeDeckToClipboard(baseCards);
        deckButtonConfirmText.setText("Deck copied to clipboard.");
    }

    @FXML
    public void randomCardFromTeam() {
        ActiveCard randomCard = adventure.getActiveCards().getRandom();
        CardDisplayPopup popup = new CardDisplayPopup(mainDatabase, randomCard,
                randomCardFromTeamButton.localToScreen(100.0, 0.0));
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                popup.hide();
            }
        });
    }

    @FXML
    public void randomCardFromDeck() {
        ActiveCardList cards = deckGridController.getChosenCards();
        if (!cards.isEmpty()) {
            ActiveCard randomCard = deckGridController.getChosenCards().getRandom();
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
    public void saveGridNode(ControlNode<ActiveCard> node) {

    }

    @Override
    public void createTooltip(ControlNode<ActiveCard> n) {

    }

    @Override
    public void createContextMenu(ControlNode<ActiveCard> n) {

    }

/*    private MenuItem createCaptureItem(ControlNode<ActiveCard> n) {
        ActiveCard c = n.getSubject();
        MenuItem captureItem = new MenuItem("Capture");
        ImageView icon = new ImageView(mainDatabase.grabIcon(IconConstant.CAPTURE));
        icon.setFitWidth(20);
        icon.setFitHeight(20);
        captureItem.setGraphic(icon);
        captureItem.setOnAction(e -> {
            adventure.captureCard(c);
            allSelectableCards.removeObject(c);
            deckGridController.removeObject(c);
        });
        return captureItem;
    }*/

/*    private MenuItem createWoundItem(ControlNode<ActiveCard> n) {
        ActiveCard c = n.getSubject();
        MenuItem woundItem;
        ImageView icon;
        if (c.hasStatus(StatusEffect.WOUND)) {
            woundItem = new MenuItem("Heal");
            icon = new ImageView(mainDatabase.grabIcon(IconConstant.HEAL));
            woundItem.setGraphic(icon);
        } else {
            woundItem = new MenuItem("Wound");
            icon = new ImageView(mainDatabase.grabIcon(IconConstant.WOUND));
            woundItem.setGraphic(icon);
        }
        woundItem.setOnAction(e -> {
            c.toggleStatus(StatusEffect.WOUND);
            logger.info(n.getSubject() + "'s wound status set to "
                    + n.getSubject().hasStatus(StatusEffect.WOUND));
            allSelectableCards.refresh();
            deckGridController.refresh();
            createContextMenu(n);
        });
        icon.setFitWidth(20);
        icon.setFitHeight(20);
        return woundItem;
    }*/

    public void toggleNodeLight(ActiveCard c) {
        allSelectableCards.toggleNodeLight(c);
    }

    @Override
    public void setMouseEvents(ControlNode<ActiveCard> node) {
        ActiveCard card = node.getSubject();
        node.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                boolean toggled = deckGridController.toggleEntry(card);
                saveDeckProfile();
                if (toggled)
                    node.toggleNodeLight();
                e.consume();
            }
        });
    }

    @FXML
    public void confirmDeck() {
        copyToClipboard();
        ActiveCardList deck = deckGridController.getDeck();
        adventure.updateDeckProfiles(deckProfiles, getProfileNum());
        AdvMatchResultPopup resultPopup = new AdvMatchResultPopup();
        resultPopup.initialize(!deck.hasNoCaptains());
        resultPopup.centerToParent(getCurrentScene().getWindow());
        Optional<AdvMatchResult> result = resultPopup.showAndWait();
        if (result.isPresent()) {
            adventure.updateStats(deck, result.get());
            captureOrWoundCardOption(deck);
            if (resultPopup.doesCapture())
                captainCaptureOption(deck);

            ActiveCardList recoveredCards = adventure.recoverCards(deck);
            ActiveCardList exhaustedCards = adventure.exhaustCards(deck);
            if (!(exhaustedCards.isEmpty() && recoveredCards.isEmpty())) {
                CardExhaustionConfirmationDialog cardDisplayConfirmationDialog = new CardExhaustionConfirmationDialog();
                cardDisplayConfirmationDialog.initialize(mainDatabase, exhaustedCards,
                        recoveredCards);
                cardDisplayConfirmationDialog.showAndWait();
            }
            logger.info(deck + getResultString(result.get()));
            changeScene(backPane);
        }
    }

    private void captainCaptureOption(ActiveCardList deck) {
        SimpleChooserDialog<ActiveCard> captainChoice = new SimpleChooserDialog<>();
        captainChoice.initialize(mainDatabase, deck.getCaptains(), TargetType.CARD);
        Optional<ActiveCard> capturingCaptain = captainChoice.showAndWait();
        if (capturingCaptain.isPresent()) {
            CardSearchSelectDialog cardSearchSelectDialog = new CardSearchSelectDialog();
            cardSearchSelectDialog.initialize(mainDatabase, adventure.getFreeAgents());
            Optional<ActiveCard> card = cardSearchSelectDialog.showAndWait();
            card.ifPresent(activeCard -> {
                adventure.sendAway(adventure.getCurrentWorldNum() + 1, capturingCaptain.get());
                adventure.addCardToTeam(activeCard);
            });
        }

    }

    private void captureOrWoundCardOption(ActiveCardList deck) {
        WoundCaptureChoiceDialog woundCaptureChoice = new WoundCaptureChoiceDialog();
        woundCaptureChoice.initialize(mainDatabase, deck);
        Optional<ActiveCard> targetCard = woundCaptureChoice.showAndWait();
        if (woundCaptureChoice.captureOptionSelected() && targetCard.isPresent()) {
            adventure.captureCard(targetCard.get());
        } else if (woundCaptureChoice.woundOptionSelected() && targetCard.isPresent()) {
            targetCard.get().setStatus(StatusEffect.WOUND, true);
        }
    }

    private String getResultString(AdvMatchResult result) {
        return switch (result) {
            case FORCE_RETREAT -> " forced the enemy to retreat.";
            case WIN -> " achieved a decisive victory.";
            case LOSE -> " suffered a humiliating defeat.";
            default -> " managed to get away safely.";
        };

    }

    private int getProfileNum() {
        return profileNum;
    }

    public void pasteFromClipboard() {
        DeckCodeConverter codeConverter = new DeckCodeConverter();
        String data = (String) Clipboard.getSystemClipboard().getContent(DataFormat.PLAIN_TEXT);
        CardList pastedDeck = codeConverter.convertDeckCodeToDeck(mainDatabase.lookupDatabase(TargetType.CARD), data);
        ActiveCardList cardList = adventure.lookupActiveCards(pastedDeck);
        ActiveCardList verifiedCards = verifyDeck(cardList);
        if (!verifiedCards.isEmpty()) {
            clearDeck();
            for (ActiveCard c : cardList) {
                deckGridController.toggleEntry(c);
                toggleNodeLight(c);
            }
            deckButtonConfirmText.setText("Deck Pasted from Clipboard");
        } else {
            deckButtonConfirmText.setText("No valid cards found to paste");
        }
    }

    public void clearDeck() {
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

    private ActiveCardList verifyDeck(ActiveCardList deck) {
        ActiveCardList validCards = new ActiveCardList(new ArrayList<>());
        for (ActiveCard c : deck) {
            if (adventure.getActiveCards().contains(c) && !c.hasStatus(StatusEffect.EXHAUSTED))
                validCards.add(c);
        }
        return validCards;
    }

    private DeckProfileList verifyDeckProfiles(DeckProfileList profileList) {
        DeckProfileList newProfileList = new DeckProfileList(profileList);
        for (int i = 0; i < profileList.size(); i++) {
            newProfileList.setProfile(i, profileList.getProfile(i));
        }
        profileNum = newProfileList.getLatestProfileNum();
        return newProfileList;
    }

    @FXML
    public void goBack() {
        adventure.updateDeckProfiles(deckProfiles, getProfileNum());
        changeScene(backPane);
    }

    public void switchProfile(int pNum) {
        saveDeckProfile();
        clearDeck();
        ActiveCardList newDeck = verifyDeck(deckProfiles.getProfile(pNum));
        for (ActiveCard c : newDeck) {
            deckGridController.toggleEntry(c);
            toggleNodeLight(c);
        }
        profileNum = pNum;
        int proFileNumDisplay = pNum + 1;
        deckProfiles.setLatestProfileNum(profileNum);
        deckButtonConfirmText.setText("Switched to Profile " + proFileNumDisplay);
    }

    @FXML
    public void switchProfile1() {
        switchProfile(0);
    }

    @FXML
    public void switchProfile2() {
        switchProfile(1);
    }

    @FXML
    public void switchProfile3() {
        switchProfile(2);
    }

    @FXML
    public void switchProfile4() {
        switchProfile(3);
    }

    public void saveDeckProfile() {
        deckProfiles.setProfile(profileNum, deckGridController.getDeck());
    }

    @Override
    public void changeScene(FullViewPane pane) {
        super.changeScene(pane);
    }
}
