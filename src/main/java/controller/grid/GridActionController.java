package controller.grid;

import controller.ControllerDatabase;
import model.thing.Thing;
import view.IconImage;
import view.ViewSize;
import view.node.control.ControlNode;

public interface GridActionController<T extends Thing> {

    public ControlNode<T> createControlNode(T t, IconImage i, ViewSize v, boolean blind);
    //Blind boolean specifies whether Captain status should be visible
    public ControllerDatabase getDatabase();
    public void saveGridNode(ControlNode<T> node);

    public void createTooltip(ControlNode<T> n);

    public void createContextMenu(ControlNode<T> n);

    //SetMouseEvents is currently determined in the ControlNode class. Set it here to make the highlight/lowlight less
    //less universal
    public void setMouseEvents(ControlNode<T> displayControlNode);
}
