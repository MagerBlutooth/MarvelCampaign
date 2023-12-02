package records.view;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import records.controller.GridRecordDisplayController;
import records.view.fxml.FXMLRecordGrabber;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.model.target.*;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLMainGrabber;

public class GridRecordDisplayNode<T extends SnapTarget> extends ScrollPane {

    @FXML
    GridRecordDisplayController gridDisplayController;

    public GridRecordDisplayNode() {
        FXMLRecordGrabber fxmlRecordGrabber = new FXMLRecordGrabber();
        fxmlRecordGrabber.grabFXML("gridRecordDisplayNode.fxml", this);
        gridDisplayController = fxmlRecordGrabber.getController();
    }

    public void initialize(CardList list, GridActionController<Card> controller, ViewSize v,
                           boolean statusVisible)
    {
        gridDisplayController.initialize(list, TargetType.CARD, controller, v, statusVisible);
    }

    public GridRecordDisplayController getController() {
        return gridDisplayController;
    }

    public void refresh()
    {
        gridDisplayController.refresh();
    }

    public void setPrefColumns(int i) {
        gridDisplayController.setPrefColumns(i);
    }
}
