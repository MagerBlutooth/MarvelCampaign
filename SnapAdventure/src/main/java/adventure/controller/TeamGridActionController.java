package adventure.controller;

import campaign.controller.MainDatabase;
import campaign.controller.grid.GridActionController;
import campaign.model.thing.Card;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class TeamGridActionController implements GridActionController<Card> {

    MainDatabase mainDatabase;
    TeamDisplayNodeController teamDisplayNodeController;

    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(getDatabase(), card, i, v, blind);
        createContextMenu(node);
        return node;
    }

    @Override
    public MainDatabase getDatabase() {
        return mainDatabase;
    }

    @Override
    public void saveGridNode(ControlNode<Card> node) {
        teamDisplayNodeController.update(node.getSubject());
    }

    @Override
    public void createTooltip(ControlNode<Card> n) {

    }

    @Override
    public void createContextMenu(ControlNode<Card> n) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem woundItem = setWoundItem(n.getSubject());
        MenuItem eliminateItem = new MenuItem("Eliminate");
        eliminateItem.setOnAction(actionEvent -> teamDisplayNodeController.eliminate(n.getSubject()));
        contextMenu.getItems().add(woundItem);
        contextMenu.getItems().add(eliminateItem);
        n.setOnContextMenuRequested(e -> contextMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    private MenuItem setWoundItem(Card c) {
        MenuItem woundItem = new MenuItem();
        if(c.isWounded()) {
            woundItem.setText("Heal");
        }
        else
            woundItem.setText("Wound");

        woundItem.setOnAction(actionEvent -> {
            teamDisplayNodeController.toggleWound(c);
            if (c.isWounded()) {
                woundItem.setText("Heal");
            } else
                woundItem.setText("Wound");
        });
        return woundItem;
    }

    @Override
    public void setMouseEvents(ControlNode<Card> displayControlNode) {

    }

    public void initialize(MainDatabase d, TeamDisplayNodeController tController) {
        mainDatabase = d;
        teamDisplayNodeController = tController;
    }
}
