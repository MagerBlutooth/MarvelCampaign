package controller.grid;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.database.ThingDatabase;
import model.logger.MLogger;
import model.thing.Card;
import model.thing.Faction;
import model.thing.Location;
import model.thing.ThingType;
import view.IconImage;
import view.ViewSize;
import view.dragdrop.Draggable;
import view.node.DroppableLocationDisplayNode;
import view.node.control.ControlNode;

import java.util.List;

public class DroppableLocationDisplayController extends GridDisplayController<Location> {

    Faction faction;

    public void initialize(Faction f, ThingType tType, GridActionController<Location> controller, ViewSize v, boolean blind)
    {
        super.initialize(f.getActiveLocationsAndMedbay(), tType, controller, v, blind);
        faction = f;
    }
    MLogger logger = new MLogger(DroppableLocationDisplayNode.class);
    @Override
    void addNewNode(Location l, List<ControlNode<Location>> listOfObjects) {
        IconImage i = controllerDatabase.grabImage(l, ThingType.LOCATION);
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
            ThingDatabase<Card> cards = controllerDatabase.lookupDatabase(ThingType.CARD);
            Object source = event.getGestureSource();
            if (source instanceof Draggable && dragboard.hasString()) {
                String dragID = dragboard.getString();
                Card c = cards.lookup(Integer.parseInt(dragID));
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
        return thingList.getThings();
    }
}
