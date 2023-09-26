package records.controller;

import campaign.controller.BasePaneController;
import campaign.controller.ControllerDatabase;
import campaign.controller.grid.GridActionController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import campaign.model.constants.CampaignConstants;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.CardList;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.manager.CardManager;
import campaign.view.node.GridDisplayNode;
import campaign.view.node.control.ControlNode;
import campaign.view.pane.editor.CardEditorPane;
import records.model.*;
import records.view.HallOfFameManagerPane;
import campaign.view.thing.CardView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HallOfFameEntryCreatorPaneController extends BasePaneController implements GridActionController<Card> {

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
    Button saveButton;
    List<HallOfFameEntry> otherEntries;
    RecordSaver saver = new RecordSaver();
    HallOfFameEntry hallOfFameEntry;
    @Override
    public Scene getCurrentScene() {
        return cardManager.getScene();
    }

    public void initialize(ControllerDatabase d, HallOfFameEntry entry, List<HallOfFameEntry> other) {
        super.initialize(d);
        hallOfFameEntry = entry;
        otherEntries = other;
        nameBar.setText(entry.getName());
        CardList cards = new CardList(d.getCards());
        captainDisplay.initialize(controllerDatabase, entry.getCaptain(), ViewSize.LARGE, false);
        deckController = new HallOfFameGridController();
        deckController.initialize(controllerDatabase, captainDisplay, deckDisplay, entry, otherEntries);
        deckDisplay.initialize(entry.getCards(), ThingType.CARD, deckController, ViewSize.SMALL, true);
        cardManager.initialize(cards, ThingType.CARD, this, ViewSize.MEDIUM, true);
        initializeSearchBar(cards);
        initializeDateChoice();
    }

    private void initializeDateChoice() {
        monthBox.setItems(FXCollections.observableArrayList(SnapMonth.values()));
        for(int i = CampaignConstants.STARTING_YEAR; i <= Calendar.getInstance().get(Calendar.YEAR); i++)
            yearBox.getItems().add(i);
        monthBox.setValue(hallOfFameEntry.getMonth());
        yearBox.setValue(hallOfFameEntry.getYear());
    }

    private void initializeSearchBar(CardList allCards) {
        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            CardList cards = new CardList(new ArrayList<>());
            for(Card c: allCards)
            {
                String name = c.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if(name.contains(searchString))
                    cards.add(c);
            }
            cardManager.initialize(cards, ThingType.CARD, this, ViewSize.MEDIUM, true);
        });
    }

    public void addNewEntry()
    {
        CardEditorPane cardEditorPane = new CardEditorPane();
        cardEditorPane.initialize(controllerDatabase, ViewSize.LARGE, new Card());
        changeScene(cardEditorPane);
    }

    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(controllerDatabase, card, i, v, blind);
        setMouseEvents(node);
        node.highlight(); //Adding highlight call to remove enable-based lowlight for Hall of Fame
        node.setGolden(isGolden(card));
        return node;
    }

    private boolean isGolden(Card c) {
        for(HallOfFameEntry entry: otherEntries)
        {
            if(entry.contains(c))
                return true;
        }
        return hallOfFameEntry.contains(c);
    }


    @Override
    public void saveGridNode(ControlNode<Card> node) {

    }

    @FXML
    public void goBack()
    {
        HallOfFameManagerPane managerPane = new HallOfFameManagerPane();
        managerPane.initialize(controllerDatabase);
        changeScene(managerPane);
    }
    @FXML
    public void saveEntry()
    {
        HallOfFameEntry entry = deckController.getActiveEntry();
        entry.setName(nameBar.getText());
        entry.setMonth(monthBox.getValue());
        entry.setYear(yearBox.getValue());
        if(entry.isValid()) {
            ThingDatabase<HallOfFameEntry> hallOfFameEntries = new ThingDatabase<>();
            hallOfFameEntries.addAll(otherEntries);
            hallOfFameEntries.addNewEntry(entry);
            saver.saveHallOfFame(RecordConstants.HOF_FILE, hallOfFameEntries);
            goBack();
        }
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
        }});
    }
}
