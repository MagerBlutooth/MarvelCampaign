package adventure.controller;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.Card;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class TempGridActionController implements GridActionController<Card> {

    MainDatabase mainDatabase;
    TeamDisplayNodeController teamDisplayNodeController;

    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(getDatabase(), card, i, v, blind);
        if(!card.notActualCard())
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
        MenuItem freeItem = new MenuItem("Defect");
        freeItem.setOnAction(actionEvent -> teamDisplayNodeController.makeCardFreeAgent(n.getSubject()));
        MenuItem teamItem = new MenuItem("To Team");
        teamItem.setOnAction(actionEvent -> teamDisplayNodeController.fromTempToTeam(n.getSubject()));
        contextMenu.getItems().add(teamItem);
        contextMenu.getItems().add(freeItem);
        n.setOnContextMenuRequested(e -> contextMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<Card> displayControlNode) {

    }

    public void initialize(MainDatabase d, TeamDisplayNodeController tController) {
        mainDatabase = d;
        teamDisplayNodeController = tController;
    }
}
