package campaign.controller.grid;

import javafx.fxml.FXML;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import campaign.model.database.ThingDatabase;
import campaign.model.logger.MLogger;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingList;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.dragdrop.Draggable;
import campaign.view.node.DraggableThingDisplayNode;
import campaign.view.node.control.ControlNode;

import java.util.List;

public class DraggableThingDisplayController<T extends Thing> extends GridDisplayController<T> {

    MLogger logger = new MLogger(DraggableThingDisplayController.class);
    @FXML
    DraggableThingDisplayNode<T> draggableThingDisplayNode;

    @Override
    public void initialize(ThingList<T> things, ThingType tType, GridActionController<T> controller, ViewSize v, boolean blind)
    {
        super.initialize(things, tType, controller, v, blind);
    }

    @Override
    protected void addNewNode(T t, List<ControlNode<T>> listOfObjects) {
        IconImage i = controllerDatabase.grabImage(t, t.getThingType());
        ControlNode<T> n = gridActionController.createControlNode(t, i, viewSize, blind);
        addDragDetected(n);
        addDragOver();
        addDragEntered();
        addDragExited();
        addDragDropped();
        listOfObjects.add(n);
    }

    private void addDragOver() {
        draggableThingDisplayNode.setOnDragOver(event -> {
            if (event.getGestureSource() != draggableThingDisplayNode && event.getDragboard().hasString()) {
                Object source = event.getGestureSource();
                Draggable<T> dragSource = (Draggable) source;
                if(dragSource.getThingType() == getThingType())
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
    }

    private void addDragEntered() {
        draggableThingDisplayNode.setOnDragEntered(event ->
        {
            if (event.getDragboard().hasString()) {
                draggableThingDisplayNode.setStyle("-fx-border-color: dodgerblue;");
            }
        });
    }

    private void addDragExited() {
        draggableThingDisplayNode.setOnDragExited(event -> {
            draggableThingDisplayNode.setStyle(null);
        });
    }

    private void addDragDropped() {
        draggableThingDisplayNode.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            Object source = event.getGestureSource();
            ThingDatabase<T> things = controllerDatabase.lookupDatabase(getThingType());
            if (source instanceof Draggable && dragboard.hasString()) {
                String dragID = dragboard.getString();
                T t = things.lookup(Integer.parseInt(dragID));
                Draggable<T> dragSource = (Draggable) source;
                if (t != null && dragSource.getThingType() == t.getThingType()) {
                    dragSource.removeOnDrag(t);
                    draggableThingDisplayNode.add(t);
                }
                event.setDropCompleted(true);
                event.consume();
            }
        });
    }
    private void addDragDetected(ControlNode<T> n) {
        n.setOnDragDetected(event -> {
            Dragboard dragboard = n.startDragAndDrop(TransferMode.MOVE);
            dragboard.setDragView(n.snapshot(null, null));
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(String.valueOf(n.getSubject().getID()));
            dragboard.setContent(clipboardContent);
            logger.debug("Dragging Content: " + dragboard.getString());
            event.consume();
        });
    }

    public void remove(T t) {
        thingList.remove(t);
        populateDisplay();
    }

    public void add(T t) {
        if(!thingList.contains(t))
            thingList.add(t);
        populateDisplay();
    }

    public List<T> getThings() {
        return thingList.getThings();
    }
}


