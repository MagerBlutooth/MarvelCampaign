package controller.grid;


import controller.ControllerDatabase;
import javafx.scene.control.Dialog;
import javafx.scene.layout.StackPane;
import model.thing.Thing;
import view.IconImage;
import view.ViewSize;
import view.dialog.CardSelectDialog;
import view.dialog.SelectDialog;
import view.node.control.ControlNode;

public class DialogGridActionController<T extends Thing> extends BaseGridActionController<T> {

    SelectDialog<T> dialog;

    public void intialize(ControllerDatabase cd, SelectDialog<T> d)
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
