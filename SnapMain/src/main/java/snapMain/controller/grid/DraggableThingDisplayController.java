package snapMain.controller.grid;

import javafx.fxml.FXML;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import snapMain.model.database.TargetDatabase;
import snapMain.model.logger.MLogger;
import snapMain.model.thing.BaseObject;
import snapMain.model.thing.ThingList;
import snapMain.model.thing.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.dragdrop.Draggable;
import snapMain.view.node.DraggableThingDisplayNode;
import snapMain.view.node.control.ControlNode;

import java.util.List;

public class DraggableThingDisplayController<T extends BaseObject> extends GridDisplayController<T> {

    MLogger logger = new MLogger(DraggableThingDisplayController.class);
    @FXML
    DraggableThingDisplayNode<T> draggableThingDisplayNode;

    @Override
    public void initialize(ThingList<T> things, TargetType tType, GridActionController<T> controller, ViewSize v, boolean blind)
    {
        super.initialize(things, tType, controller, v, blind);
    }

    @Override
    protected void addNewNode(T t, List<ControlNode<T>> listOfObjects) {
        IconImage i = mainDatabase.grabImage(t, t.getTargetType());
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
            TargetDatabase<T> things = mainDatabase.lookupDatabase(getThingType());
            if (source instanceof Draggable && dragboard.hasString()) {
                String dragID = dragboard.getString();
                T t = things.lookup(Integer.parseInt(dragID));
                Draggable<T> dragSource = (Draggable) source;
                if (t != null && dragSource.getThingType() == t.getTargetType()) {
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


