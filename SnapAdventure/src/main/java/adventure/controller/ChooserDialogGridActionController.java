package adventure.controller;


import adventure.view.popup.ChooserDialog;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.SnapTarget;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.dialog.SearchSelectDialog;
import snapMain.view.node.control.ControlNode;

public class ChooserDialogGridActionController<T extends SnapTarget> extends BaseGridActionController<T> {

    ChooserDialog<T> dialog;

    public void initialize(MainDatabase cd, ChooserDialog<T> d)
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
