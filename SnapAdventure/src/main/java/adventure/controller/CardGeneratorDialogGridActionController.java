package adventure.controller;


import adventure.view.popup.CardGeneratorDialog;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.Card;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class CardGeneratorDialogGridActionController extends BaseGridActionController<Card> {

    CardGeneratorDialog dialog;

    public void initialize(MainDatabase cd, CardGeneratorDialog d)
    {
        super.initialize(cd);
        dialog = d;
    }

    @Override
    public ControlNode<Card> createControlNode(Card t, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(getDatabase(), t, i, v, blind);
        return node;
    }
}
