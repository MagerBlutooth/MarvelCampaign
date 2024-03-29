package snapMain.controller.grid;

import snapMain.controller.MainDatabase;
import snapMain.model.target.SnapTarget;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public interface GridActionController<T extends SnapTarget> {

    public ControlNode<T> createControlNode(T t, IconImage i, ViewSize v, boolean blind);
    ControlNode<T> createEmptyNode(ViewSize v);
    //Blind boolean specifies whether Captain status should be visible
    public MainDatabase getDatabase();
    public void saveGridNode(ControlNode<T> node);

    public void createTooltip(ControlNode<T> n);

    public void createContextMenu(ControlNode<T> n);

    //SetMouseEvents is currently determined in the ControlNode class. Set it here to make the highlight/lowlight less
    //less universal
    public void setMouseEvents(ControlNode<T> displayControlNode);
}
