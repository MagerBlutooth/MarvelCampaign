package controller.grid;

import controller.ControllerDatabase;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.thing.Card;
import model.thing.HallOfFameEntry;
import view.IconImage;
import view.ViewSize;
import view.node.GridDisplayNode;
import view.node.control.ControlNode;
import view.thing.CardView;

import java.util.List;

public class HallOfFameGridController implements GridActionController<Card> {

    ControllerDatabase controllerDatabase;
    List<HallOfFameEntry> otherEntries;
    HallOfFameEntry activeEntry;
    CardView captainDisplay;
    GridDisplayNode<Card> deckDisplay;

    public void initialize(ControllerDatabase db, CardView cDisplay, GridDisplayNode<Card> deck, HallOfFameEntry entry, List<HallOfFameEntry> other)
    {
        controllerDatabase = db;
        activeEntry = entry;
        deckDisplay = deck;
        otherEntries = other;
        captainDisplay = cDisplay;
        captainDisplay.disableTooltip();
        deckDisplay.setPrefColumns(6);
        deckDisplay.setMaxHeight(250.0);
        deckDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> controlNode = new ControlNode<>();
        controlNode.initialize(controllerDatabase, card, i, v, blind);
        setMouseEvents(controlNode);
        return controlNode;
    }

    @Override
    public ControllerDatabase getDatabase() {
        return controllerDatabase;
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
                activeEntry.setCaptain(controlNode.getSubject());
                captainDisplay.refresh(activeEntry.getCaptain());
            }
            e.consume();
        });
    }

    public void toggleEntry(Card card) {
        boolean success;
        if(activeEntry.contains(card)) {
            success = activeEntry.removeCard(card);
            if(activeEntry.getCaptain().equals(card))
                activeEntry.setCaptain(new Card());
        }
        else {
            success = activeEntry.addCard(card, otherEntries);
        }
        if(success)
            deckDisplay.refreshToMatch(activeEntry.getCards());
    }

    public HallOfFameEntry getActiveEntry() {
        return activeEntry;
    }
}
