package records.controller;

import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import snapMain.controller.MainDatabase;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.*;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

import java.util.ArrayList;
import java.util.List;

public class DeckCheckGridActionController extends BaseGridActionController<Card> {

    List<ControlNode<Card>> nodes;
    public DeckCheckGridActionController()
    {
        nodes = new ArrayList<>();
    }
    @Override
    public ControlNode<Card> createControlNode(Card t, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(getDatabase(), t, i, v, blind);
        nodes.add(node);
        return node;
    }

    @Override
    public ControlNode<Card> createEmptyNode(ViewSize v) {
        ControlNode<Card> blankNode = new ControlNode<>();
        blankNode.initialize(getDatabase().grabBlankImage(TargetType.CARD),
                v);
        return blankNode;
    }

    @Override
    public void saveGridNode(ControlNode<Card> node) {

    }

    @Override
    public void createTooltip(ControlNode<Card> n) {
        /*Tooltip tooltip = new Tooltip();
        ImageView tooltipImage = new ImageView(mainDatabase.grabImage(n.getSubject()));
        tooltipImage.setFitHeight(ViewSize.MEDIUM.getSizeVal());
        tooltipImage.setFitWidth(ViewSize.MEDIUM.getSizeVal());
        tooltip.setGraphic(tooltipImage);
        tooltip.setText(n.getSubject().getName());
        tooltip.setShowDelay(new Duration(1.0));
        Tooltip.install(n, tooltip);*/
    }

    @Override
    public void createContextMenu(ControlNode<Card> n) {

    }

    @Override
    public void setMouseEvents(ControlNode<Card> displayControlNode) {

    }

    public void setInvalidCards(CardList invalidCards) {

        for(ControlNode<Card> n: nodes)
        {
            if(invalidCards.contains(n.getSubject()))
                n.setInvalid(true);
        }
    }
}
