package campaign.controller.grid;

import campaign.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.CardList;
import campaign.model.thing.Faction;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.dragdrop.Draggable;
import campaign.view.node.AgentSelectorGrid;
import campaign.view.node.DraggableThingDisplayNode;
import campaign.view.node.control.ControlNode;
import campaign.view.node.control.DraggableControlNode;

import java.util.List;

public class AgentSelectorGridController extends ThingActionController<Card> {

    @FXML
    AgentSelectorGrid agentSelectorGrid;
    @FXML
    DraggableThingDisplayNode<Card> agentDisplay;

    Faction faction;

    public void initialize(MainDatabase database, Faction f, boolean blind) {
        mainDatabase = database;
        faction = f;
        CardList factionAgents = new CardList(faction.getAgentsInHQ());
        factionAgents.sort();
        agentDisplay.initialize(factionAgents, ThingType.CARD, this,ViewSize.TINY, blind);
        addDragOver();
        addDragEntered();
        addDragExited();
        addDragDropped();
    }

    private void addDragOver() {
        agentSelectorGrid.setOnDragOver(event -> {
            if (event.getGestureSource() != agentSelectorGrid && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
    }

    public void addCard(Card c) {
        agentDisplay.add(c);
    }
    private void addDragEntered() {
        agentSelectorGrid.setOnDragEntered(event ->
        {
            if (event.getDragboard().hasString()) {
                agentSelectorGrid.setStyle("-fx-border-color: dodgerblue;");
            }
        });
    }

    private void addDragExited() {
        agentSelectorGrid.setOnDragExited(event -> {
            agentSelectorGrid.setStyle(null);
        });
    }

    private void addDragDropped() {
        agentSelectorGrid.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            Object source = event.getGestureSource();
            ThingDatabase<Card> cards = mainDatabase.lookupDatabase(ThingType.CARD);
            if (source instanceof Draggable && dragboard.hasString()) {
                String dragID = dragboard.getString();
                Card c = cards.lookup(Integer.parseInt(dragID));
                if (c != null) {
                    Draggable<Card> dragSource = (Draggable) source;
                    dragSource.removeOnDrag(c);
                    addCard(c);
                }
                event.setDropCompleted(true);
                event.consume();
            }
        });
    }
    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new DraggableControlNode<>(agentDisplay.getController());
        node.initialize(mainDatabase, card, i, v, blind);
        createTooltip(node);
        //setMouseEvents(node);
        return node;
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
    public void setMouseEvents(ControlNode<Card> displayControlNode) {

    }
    public void sortBy(String s) {
        agentDisplay.sortBy(s);
    }

    public void filterBy(String s, boolean notSelected)
    {
        agentDisplay.filterBy(s, notSelected);
    }

    public List<Card> getAgents() {
        return agentDisplay.getThings();
    }

    public void reset() {
        agentDisplay.refreshToMatch(faction.getOwnedAgents());
    }
}
