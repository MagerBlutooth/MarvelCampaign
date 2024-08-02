package records.controller;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;
import snapMain.view.thing.CardView;
import records.model.HallOfFameEntry;

import java.util.List;

public class HallOfFameGridController implements GridActionController<Card> {

    MainDatabase mainDatabase;
    List<HallOfFameEntry> otherEntries;
    HallOfFameEntry activeEntry;
    CardView captainDisplay;
    GridDisplayNode<Card> deckDisplay;

    public void initialize(MainDatabase db, CardView cDisplay, GridDisplayNode<Card> deck, HallOfFameEntry entry, List<HallOfFameEntry> other)
    {
        mainDatabase = db;
        activeEntry = entry;
        deckDisplay = deck;
        otherEntries = other;
        captainDisplay = cDisplay;
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
    public ControlNode<Card> createEmptyNode(ViewSize v) {
        ControlNode<Card> cardNode = new ControlNode<>();
        cardNode.initialize(mainDatabase, new Card(), mainDatabase.grabBlankImage(TargetType.CARD),
                v,false);
        return cardNode;
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
            if (e.getButton() == MouseButton.PRIMARY && captainIsUnique(controlNode.getSubject())) {
                activeEntry.setCaptain(controlNode.getSubject());
                captainDisplay.refresh(activeEntry.getCaptain());
            }
            e.consume();
        });
    }

    //Check to confirm captains are unique among Hall of Fame entries.
    private boolean captainIsUnique(Card captain) {
        for(HallOfFameEntry entry: otherEntries)
        {
            if(entry.getCaptain().equals(captain))
                return false;
        }
        return true;
    }

    public void toggleEntry(Card card) {
        boolean success;
        if(activeEntry.contains(card)) {
            success = activeEntry.removeCard(card);
            if(activeEntry.getCaptain().equals(card))
            {
                Card newCard = new Card();
                activeEntry.setCaptain(newCard);
                captainDisplay.refresh(newCard);
            }
        }
        else {
            success = activeEntry.addCard(card, otherEntries).getResult();
            updateUniqueCardStatus();
        }
        if(success)
            deckDisplay.refreshToMatch(activeEntry.getCards());
    }

    private void updateUniqueCardStatus() {
        activeEntry.checkCardCountProperties(otherEntries);
    }

    public HallOfFameEntry getActiveEntry() {
        return activeEntry;
    }
}
