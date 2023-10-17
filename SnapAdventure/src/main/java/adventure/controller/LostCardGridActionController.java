package adventure.controller;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.model.target.Card;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class LostCardGridActionController implements GridActionController<Card> {

    MainDatabase mainDatabase;
    TeamDisplayNodeController teamDisplayNodeController;
    GridDisplayController<Card> ownGridController;

    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(getDatabase(), card, i, v, blind);
        if(card.isActualThing())
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
        MenuItem retrieveItem = new MenuItem("Retrieve");
        retrieveItem.setOnAction(actionEvent -> retrieveCard(n.getSubject()));
        contextMenu.getItems().add(retrieveItem);
        n.setOnContextMenuRequested(e -> contextMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    private void retrieveCard(Card c) {
        teamDisplayNodeController.returnCard(c);
        ownGridController.removeThing(c);
    }

    @Override
    public void setMouseEvents(ControlNode<Card> displayControlNode) {

    }

    public void initialize(MainDatabase d, TeamDisplayNodeController tController,
                           GridDisplayController<Card> ownController) {
        mainDatabase = d;
        teamDisplayNodeController = tController;
        ownGridController = ownController;
    }
}
