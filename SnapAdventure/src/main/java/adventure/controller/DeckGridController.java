package adventure.controller;

import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

import java.util.ArrayList;

public class DeckGridController implements GridActionController<Card> {

    MainDatabase mainDatabase;
    CardList chosenCards;
    GridDisplayNode<Card> deckDisplay;

    DeckConstructorDialogController deckConstructorController;

    public void initialize(MainDatabase db, GridDisplayNode<Card> dDisplay, DeckConstructorDialogController cController)
    {
        mainDatabase = db;
        chosenCards = new CardList(new ArrayList<>());
        deckDisplay = dDisplay;
        deckDisplay.setPrefColumns(6);
        deckDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        deckConstructorController = cController;
    }

    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> controlNode = new ControlNode<>();
        controlNode.initialize(mainDatabase, card, i, v, blind);
        setMouseEvents(controlNode);
        return controlNode;
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
    public void setMouseEvents(ControlNode<Card> controlNode) {
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                chosenCards.remove(controlNode.getSubject());
                deckConstructorController.toggleNodeLight(controlNode.getSubject());
                deckDisplay.refreshToMatch(chosenCards);
            }
            e.consume();
        });
    }

    public boolean toggleEntry(Card card) {
        boolean toggled = false;
        if(chosenCards.contains(card)) {
            toggled = chosenCards.remove(card);
        }
        else if(chosenCards.size() < SnapMainConstants.MAX_DECK_SIZE){
            toggled = chosenCards.add(card);

        }
        deckDisplay.refreshToMatch(chosenCards);
        return toggled;
    }

    public CardList getDeck() {
        return chosenCards;
    }

    public void clear() {
        chosenCards.clear();
    }
}
