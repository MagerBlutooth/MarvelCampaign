package adventure.controller.dialog;


import adventure.model.target.ActiveCard;
import adventure.view.popup.CardGeneratorDialog;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class CardGeneratorDialogGridActionController extends BaseGridActionController<ActiveCard> {

    CardGeneratorDialog dialog;

    public void initialize(MainDatabase cd, CardGeneratorDialog d)
    {
        super.initialize(cd);
        dialog = d;
    }

    @Override
    public ControlNode<ActiveCard> createControlNode(ActiveCard t, IconImage i, ViewSize v, boolean status) {
        ControlNode<ActiveCard> node = new ControlNode<>();
        node.initialize(getDatabase(), t, i, v, status);
        return node;
    }
}
