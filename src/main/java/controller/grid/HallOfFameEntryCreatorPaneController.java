package controller.grid;

import controller.CampaignPaneController;
import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.database.ThingDatabase;
import model.database.ThingSaver;
import model.thing.Card;
import model.thing.CardList;
import model.thing.HallOfFameEntry;
import model.thing.ThingType;
import view.IconImage;
import view.ViewSize;
import view.manager.CardManager;
import view.node.GridDisplayNode;
import view.node.control.ControlNode;
import view.pane.editor.CardEditorPane;
import view.pane.manager.HallOfFameManagerPane;
import view.thing.CardView;

import java.util.ArrayList;
import java.util.List;


public class HallOfFameEntryCreatorPaneController extends CampaignPaneController implements GridActionController<Card>{

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
    Button saveButton;
    List<HallOfFameEntry> otherEntries;
    ThingSaver saver = new ThingSaver();
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
        if(entry.isValid()) {
            ThingDatabase<HallOfFameEntry> hallOfFameEntries = new ThingDatabase<>();
            hallOfFameEntries.addAll(otherEntries);
            hallOfFameEntries.addNewEntry(entry);
            saver.saveHallOfFame(hallOfFameEntries);
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
