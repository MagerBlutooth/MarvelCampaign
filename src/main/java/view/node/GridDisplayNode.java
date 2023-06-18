package view.node;

import controller.grid.GridDisplayController;
import controller.grid.GridActionController;
import javafx.scene.control.ScrollPane;
import model.logger.MLogger;
import model.thing.Thing;
import model.thing.ThingList;
import model.thing.ThingType;
import view.ViewSize;
import view.fxml.FXMLGrabber;

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

    public void refreshToMatch(ThingList<T> things) {
        gridDisplayController.refresh(things);
    }

    public void setPrefColumns(int i) {
        gridDisplayController.setPrefColumns(i);
    }
}
