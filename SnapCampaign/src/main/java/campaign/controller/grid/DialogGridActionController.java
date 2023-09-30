package campaign.controller.grid;


import campaign.controller.MainDatabase;
import campaign.model.thing.Thing;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.dialog.SelectDialog;
import campaign.view.node.control.ControlNode;

public class DialogGridActionController<T extends Thing> extends BaseGridActionController<T> {

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
