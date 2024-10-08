package records.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import records.model.*;
import records.view.HallOfFameManagerPane;
import snapMain.controller.BasePaneController;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.helper.DeckCodeConverter;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.manager.CardManager;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;
import snapMain.view.thing.CardView;

import java.util.Calendar;
import java.util.List;


public class HallOfFameEntryCreatorPaneController extends BasePaneController<MainDatabase> implements GridActionController<Card> {

    @FXML
    CardManager cardManager;
    @FXML
    CardView captainDisplay;
    @FXML
    GridDisplayNode<Card> deckDisplay;
    @FXML
    TextField searchBar;
    @FXML
    TextField nameBar;
    HallOfFameGridController deckController;
    @FXML
    ChoiceBox<SnapMonth> monthBox;
    @FXML
    ChoiceBox<Integer> yearBox;
    @FXML
    ChoiceBox<HallOfFameCategory> hallOfFameFilters;

    @FXML
    Button saveButton;
    List<HallOfFameEntry> otherEntries;
    RecordSaver saver = new RecordSaver();
    HallOfFameEntry hallOfFameEntry;

    @Override
    public Scene getCurrentScene() {
        return cardManager.getScene();
    }

    public void initialize(MainDatabase d, HallOfFameEntry entry, List<HallOfFameEntry> other) {
        super.initialize(d);
        hallOfFameEntry = entry;
        otherEntries = other;
        nameBar.setText(entry.getName());
        CardList cards = new CardList(d.getCards());
        captainDisplay.initialize(mainDatabase, entry.getCaptain(), ViewSize.LARGE, false);
        deckController = new HallOfFameGridController();
        deckController.initialize(mainDatabase, captainDisplay, deckDisplay, entry, otherEntries);
        deckDisplay.initialize(entry.getCards(), TargetType.CARD, deckController, ViewSize.SMALL, true);
        cardManager.initialize(cards, TargetType.CARD, this, ViewSize.MEDIUM, true);
        hallOfFameFilters.getItems().addAll(HallOfFameCategory.values());

        saveButton.disableProperty().bind(Bindings.greaterThan(entry.getMinUniqueCardCount(),
                entry.getUniqueCardCountProperty()).or(Bindings.greaterThan(SnapMainConstants.DECK_SIZE,
                entry.getCardCountProperty())).or(Bindings.not(entry.getCaptainSetProperty())));

        initializeSearchBar(cards);
        initializeDateChoice();
        initializeFilterButton(cards);
    }

    private void initializeFilterButton(CardList allCards) {
        hallOfFameFilters.setValue(HallOfFameCategory.ALL);
        hallOfFameFilters.setOnAction(event ->
        {
            CardList filteredCards = new CardList();
            HallOfFameCategory category = hallOfFameFilters.getValue();
            for (Card c : allCards) {
                switch (category) {
                    case HALL_OF_FAME: {
                        if (isGolden(c))
                            filteredCards.add(c);
                        break;
                    }
                    case NOT_HALL_OF_FAME: {
                        if (!isGolden(c))
                            filteredCards.add(c);
                        break;
                    }
                    default:
                        filteredCards.add(c);
                }
            }
            cardManager.initialize(filteredCards, TargetType.CARD, this, ViewSize.MEDIUM, true);
        });
    }

    private void initializeDateChoice() {
        monthBox.setItems(FXCollections.observableArrayList(SnapMonth.values()));
        for (int i = SnapMainConstants.STARTING_YEAR; i <= Calendar.getInstance().get(Calendar.YEAR); i++)
            yearBox.getItems().add(i);
        monthBox.setValue(hallOfFameEntry.getMonth());
        yearBox.setValue(hallOfFameEntry.getYear());
    }

    private void initializeSearchBar(CardList allCards) {
        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            CardList cards = new CardList();
            for (Card c : allCards) {
                String name = c.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if (name.contains(searchString))
                    cards.add(c);
            }
            cardManager.initialize(cards, TargetType.CARD, this, ViewSize.MEDIUM, true);
        });
    }

    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(mainDatabase, card, i, v, blind);
        setMouseEvents(node);
        node.highlight(); //Adding highlight call to remove enable-based lowlight for Hall of Fame
        node.setDull(!isGolden(card));
        return node;
    }

    @Override
    public ControlNode<Card> createEmptyNode(ViewSize v) {
        ControlNode<Card> cardNode = new ControlNode<>();
        cardNode.initialize(mainDatabase, new Card(), mainDatabase.grabBlankImage(TargetType.CARD),
                v, false);
        return cardNode;
    }

    private boolean isGolden(Card c) {
        for (HallOfFameEntry entry : otherEntries) {
            if (entry.contains(c))
                return true;
        }
        return hallOfFameEntry.contains(c);
    }


    @Override
    public void saveGridNode(ControlNode<Card> node) {

    }

    @FXML
    public void goBack() {
        HallOfFameManagerPane managerPane = new HallOfFameManagerPane();
        managerPane.initialize(mainDatabase);
        changeScene(managerPane);
    }

    @FXML
    public void saveEntry() {
        HallOfFameEntry entry = deckController.getActiveEntry();
        entry.setName(nameBar.getText());
        entry.setMonth(monthBox.getValue());
        entry.setYear(yearBox.getValue());
        if (entry.isSavable()) {
            TargetDatabase<HallOfFameEntry> hallOfFameEntries = new TargetDatabase<>();
            hallOfFameEntries.addAll(otherEntries);
            hallOfFameEntries.addNewEntry(entry);
            saver.saveHallOfFame(RecordConstants.HOF_FILE, hallOfFameEntries);
            goBack();
        }
    }

    @FXML
    public void convertDeckCodeToClipboard() {
        DeckCodeConverter codeConverter = new DeckCodeConverter();
        codeConverter.encodeDeckToClipboard(nameBar.getText(), hallOfFameEntry.getCards());
    }


    @Override
    public void createTooltip(ControlNode<Card> n) {

    }

    @Override
    public void createContextMenu(ControlNode<Card> n) {

    }

    @Override
    public void setMouseEvents(ControlNode<Card> controlNode) {
        Card card = controlNode.getSubject();
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                deckController.toggleEntry(card);
                e.consume();
            }
        });
    }
}
