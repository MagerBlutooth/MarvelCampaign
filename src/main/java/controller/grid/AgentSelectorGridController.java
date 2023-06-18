package controller.grid;

import controller.ControllerDatabase;
import view.dragdrop.Draggable;
import javafx.fxml.FXML;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.database.ThingDatabase;
import model.thing.*;
import view.IconImage;
import view.ViewSize;
import view.node.AgentSelectorGrid;
import view.node.DraggableThingDisplayNode;
import view.node.control.ControlNode;
import view.node.control.DraggableControlNode;

import java.util.List;

public class AgentSelectorGridController extends ThingActionController<Card> {

    @FXML
    AgentSelectorGrid agentSelectorGrid;
    @FXML
    DraggableThingDisplayNode<Card> agentDisplay;

    Faction faction;

    public void initialize(ControllerDatabase database, Faction f, boolean blind) {
        controllerDatabase = database;
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
            ThingDatabase<Card> cards = controllerDatabase.lookupDatabase(ThingType.CARD);
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
        node.initialize(controllerDatabase, card, i, v, blind);
        createTooltip(node);
        //setMouseEvents(node);
        return node;
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
