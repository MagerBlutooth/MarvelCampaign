package snapMain.controller.grid;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.Faction;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.dragdrop.Draggable;
import snapMain.view.node.AgentSelectorGrid;
import snapMain.view.node.DraggableThingDisplayNode;
import snapMain.view.node.control.ControlNode;
import snapMain.view.node.control.DraggableControlNode;

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
        agentDisplay.initialize(factionAgents, TargetType.CARD, this,ViewSize.TINY, blind);
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
            TargetDatabase<Card> cards = mainDatabase.lookupDatabase(TargetType.CARD);
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
    public ControlNode<Card> createEmptyNode(ViewSize v) {
        ControlNode<Card> cardNode = new ControlNode<>();
        cardNode.initialize(mainDatabase, new Card(), mainDatabase.grabBlankImage(TargetType.LOCATION),
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
