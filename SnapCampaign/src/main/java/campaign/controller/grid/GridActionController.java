package campaign.controller.grid;

import campaign.controller.MainDatabase;
import campaign.model.thing.Thing;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;

public interface GridActionController<T extends Thing> {

    public ControlNode<T> createControlNode(T t, IconImage i, ViewSize v, boolean blind);
    //Blind boolean specifies whether Captain status should be visible
    public MainDatabase getDatabase();
    public void saveGridNode(ControlNode<T> node);

    public void createTooltip(ControlNode<T> n);

    public void createContextMenu(ControlNode<T> n);

    //SetMouseEvents is currently determined in the ControlNode class. Set it here to make the highlight/lowlight less
    //less universal
    public void setMouseEvents(ControlNode<T> displayControlNode);
}
