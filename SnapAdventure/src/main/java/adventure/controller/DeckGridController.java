package adventure.controller;

import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;
import snapMain.view.thing.CardView;

import java.util.ArrayList;
import java.util.List;

public class DeckGridController implements GridActionController<Card> {

    MainDatabase mainDatabase;
    CardList chosenCards;
    GridDisplayNode<Card> deckDisplay;

    public void initialize(MainDatabase db, GridDisplayNode<Card> dDisplay)
    {
        mainDatabase = db;
        chosenCards = new CardList(new ArrayList<>());
        deckDisplay = dDisplay;
        deckDisplay.setPrefColumns(6);
        deckDisplay.setMaxHeight(250.0);
        deckDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
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
                deckDisplay.refreshToMatch(chosenCards);
            }
            e.consume();
        });
    }

    public void toggleEntry(Card card) {
        boolean success;
        if(chosenCards.contains(card)) {
            chosenCards.remove(card);
        }
        else {
            chosenCards.add(card);
        }
        deckDisplay.refreshToMatch(chosenCards);
    }

    public CardList getDeck() {
        return chosenCards;
    }
}
