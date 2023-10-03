package snapMain.controller.grid;


import snapMain.controller.MainDatabase;
import snapMain.model.target.BaseObject;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.dialog.SelectDialog;
import snapMain.view.node.control.ControlNode;

public class DialogGridActionController<T extends BaseObject> extends BaseGridActionController<T> {

    SelectDialog<T> dialog;

    public void intialize(MainDatabase cd, SelectDialog<T> d)
    {
        super.initialize(cd);
        dialog = d;
    }

    @Override
    public ControlNode<T> createControlNode(T t, IconImage i, ViewSize v, boolean blind) {
        ControlNode<T> node = new ControlNode<>();
        node.initialize(getDatabase(), t, i, v, blind);
        node.setOnMouseClicked(e -> dialog.setChoice(node.getSubject()));
        createTooltip(node);
        return node;
    }
}
