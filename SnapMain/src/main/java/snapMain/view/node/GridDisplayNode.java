package snapMain.view.node;

import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.model.target.SnapTarget;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLMainGrabber;
import javafx.scene.control.ScrollPane;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class GridDisplayNode<T extends SnapTarget> extends ScrollPane {

    private final GridDisplayController<T> gridDisplayController;
    private FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();

    public GridDisplayNode() {
        fxmlMainGrabber.grabFXML("gridDisplayNode.fxml", this);
        gridDisplayController = fxmlMainGrabber.getController();
    }

    public FXMLMainGrabber getFXMLGrabber()
    {
        return fxmlMainGrabber;
    }

    public void initialize(TargetList<T> list, TargetType t, GridActionController<T> controller, ViewSize v,
                           boolean statusVisible)
    {
        gridDisplayController.initialize(list, t, controller, v, statusVisible);
    }

    public void sortBy(String c) {
        gridDisplayController.sort(c);
    }

    public GridDisplayController<T> getController() {
        return gridDisplayController;
    }

    public void addTarget(T t)
    {
        gridDisplayController.addTarget(t);
    }

    public void removeTarget(T t)
    {
        gridDisplayController.removeTarget(t);
    }

    public void refreshToMatch(TargetList<T> targets) {
        gridDisplayController.refresh(targets);
    }

    public void refresh()
    {
        gridDisplayController.refresh();
    }

    public void setPrefColumns(int i) {
        gridDisplayController.setPrefColumns(i);
    }

    public void update(T subject) {
        gridDisplayController.update(subject);
    }

    public void toggleNodeLight(T t) {
        gridDisplayController.toggleNodeLight(t);
    }

    public void highlightAll() {
        gridDisplayController.highlightAll();
    }

    public void clear() {
        gridDisplayController.clear();
    }

}
