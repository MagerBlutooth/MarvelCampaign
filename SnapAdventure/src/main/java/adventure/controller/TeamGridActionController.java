package adventure.controller;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.Card;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

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
        MenuItem captureItem = new MenuItem("Capture");
        MenuItem miaItem = new MenuItem("Send Away");
        MenuItem freeItem = new MenuItem("Defect");
        MenuItem captainItem = new MenuItem("Toggle Captain");
        eliminateItem.setOnAction(actionEvent -> teamDisplayNodeController.eliminate(n.getSubject()));
        captureItem.setOnAction(actionEvent -> teamDisplayNodeController.capture(n.getSubject()));
        miaItem.setOnAction(actionEvent -> teamDisplayNodeController.sendAway(n.getSubject()));
        captainItem.setOnAction(actionEvent -> teamDisplayNodeController.toggleCaptain(n.getSubject()));
        freeItem.setOnAction(actionEvent -> teamDisplayNodeController.makeCardFreeAgent(n.getSubject()));
        contextMenu.getItems().add(woundItem);
        contextMenu.getItems().add(eliminateItem);
        contextMenu.getItems().add(captureItem);
        contextMenu.getItems().add(miaItem);
        contextMenu.getItems().add(captainItem);
        contextMenu.getItems().add(freeItem);
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
