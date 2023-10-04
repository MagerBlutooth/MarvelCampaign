package snapMain.controller.grid;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import snapMain.model.database.TargetDatabase;
import snapMain.model.logger.MLogger;
import snapMain.model.target.Card;
import snapMain.model.target.Faction;
import snapMain.model.target.Location;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.dragdrop.Draggable;
import snapMain.view.node.DroppableLocationDisplayNode;
import snapMain.view.node.control.ControlNode;

import java.util.List;

public class DroppableLocationDisplayController extends GridDisplayController<Location> {

    Faction faction;

    public void initialize(Faction f, TargetType tType, GridActionController<Location> controller, ViewSize v, boolean blind)
    {
        super.initialize(f.getActiveLocationsAndMedbay(), tType, controller, v, blind);
        faction = f;
    }
    MLogger logger = new MLogger(DroppableLocationDisplayNode.class);
    @Override
    protected void addNewNode(Location l, List<ControlNode<Location>> listOfObjects) {
        IconImage i = mainDatabase.grabImage(l);
        ControlNode<Location> n = gridActionController.createControlNode(l,i,viewSize, blind);
        n.setOnDragOver(mouseDragOver(n));
        n.setOnDragDropped(mouseDragDropped(n));
        n.setOnDragEntered(mouseDragEntered(n));
        n.setOnDragExited(mouseDragExited(n));
        listOfObjects.add(n);
    }

    private EventHandler<DragEvent> mouseDragOver(ControlNode<Location> n) {
        return event -> {
            if(event.getGestureSource() != n && event.getDragboard().hasString())
            {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        };
    }

    private EventHandler<DragEvent> mouseDragExited(ControlNode<Location> n) {
        return event -> n.setStyle(null);
    }

    private EventHandler<DragEvent> mouseDragEntered(ControlNode<Location> n) {
        return event -> {
            if(event.getDragboard().hasString())
            {
                logger.debug("Dropping Content: " + event.getDragboard().getString());
                n.setStyle("-fx-border-color: dodgerblue;");
                event.acceptTransferModes(TransferMode.MOVE);
                event.consume();
            }
        };
    }

    private EventHandler<DragEvent> mouseDragDropped(ControlNode<Location> n) {
        return event -> {
            Dragboard dragboard = event.getDragboard();
            TargetDatabase<Card> cards = mainDatabase.lookupDatabase(TargetType.CARD);
            Object source = event.getGestureSource();
            if (source instanceof Draggable && dragboard.hasString()) {
                String dragID = dragboard.getString();
                Card c = new Card(cards.lookup(Integer.parseInt(dragID)));
                if (c != null && !(n.getSubject().isFull())) {
                    Draggable dragSource = (Draggable)source;
                    dragSource.removeOnDrag(c);
                    faction.stationAgent(c, n.getSubject());
                    populateDisplay();
                }
                else
                {
                    logger.info("Location Full");
                }
            }
            event.setDropCompleted(true);
            event.consume();
        };
    }

    public List<Location> getLocations() {
        return targetList.getThings();
    }
}
