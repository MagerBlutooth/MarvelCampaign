package adventure.controller.dialog;


import adventure.view.popup.Choosable;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.SnapTarget;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class ChooserDialogGridActionController<T extends SnapTarget> extends BaseGridActionController<T> {

    Choosable<T> dialog;

    public void initialize(MainDatabase cd, Choosable<T> d)
    {
        super.initialize(cd);
        dialog = d;
    }

    @Override
    public ControlNode<T> createControlNode(T t, IconImage i, ViewSize v, boolean blind) {
        ControlNode<T> node = new ControlNode<>();
        node.initialize(getDatabase(), t, i, v, blind);
        if(node.getSubject().isActualThing())
            node.setOnMouseClicked(e -> dialog.setChoice(node.getSubject()));
        createTooltip(node);
        return node;
    }
}
