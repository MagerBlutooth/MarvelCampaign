package view.node;

import controller.grid.GridDisplayController;
import controller.grid.GridActionController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.logger.MLogger;
import model.thing.CardList;
import model.thing.Thing;
import model.thing.ThingList;
import model.thing.ThingType;
import org.slf4j.Logger;
import view.ViewSize;
import view.fxml.FXMLGrabber;

import java.util.List;
import java.util.Set;

public class GridDisplayNode<T extends Thing> extends ScrollPane {

    MLogger logger = new MLogger(GridDisplayNode.class);
    private final GridDisplayController<T> gridDisplayController;
    FXMLGrabber fxmlGrabber = new FXMLGrabber();

    public GridDisplayNode() {
        fxmlGrabber.grabFXML("gridDisplayNode.fxml", this);
        gridDisplayController = fxmlGrabber.getController();
    }

    public void initialize(ThingList<T> list, ThingType t, GridActionController<T> controller, ViewSize v, boolean blind)
    {
        gridDisplayController.initialize(list, t, controller, v, blind);
    }

    public void sortBy(String c) {
        gridDisplayController.sort(c);
    }

    public GridDisplayController<T> getController() {
        return gridDisplayController;
    }

    public void refresh(ThingList<T> things) {
        gridDisplayController.refresh(things);
    }
}
