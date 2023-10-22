package adventure.controller;

import adventure.model.target.ActiveCard;
import adventure.view.node.ActiveCardControlNode;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class LostCardGridActionController implements GridActionController<ActiveCard> {

    MainDatabase mainDatabase;
    TeamDisplayNodeController teamDisplayNodeController;
    GridDisplayController<ActiveCard> ownGridController;

    @Override
    public ControlNode<ActiveCard> createControlNode(ActiveCard card, IconImage i, ViewSize v, boolean statusVisible) {
        ActiveCardControlNode node = new ActiveCardControlNode();
        node.initialize(getDatabase(), card, i, v, statusVisible);
        if(card.isActualThing())
            createContextMenu(node);
        return node;
    }

    @Override
    public ControlNode<ActiveCard> createEmptyNode(ViewSize v) {
        ControlNode<ActiveCard> cardNode = new ControlNode<>();
        cardNode.initialize(mainDatabase, new ActiveCard(), mainDatabase.grabBlankImage(TargetType.CARD),
                v,false);
        return cardNode;
    }

    @Override
    public MainDatabase getDatabase() {
        return mainDatabase;
    }

    @Override
    public void saveGridNode(ControlNode<ActiveCard> node) {
        teamDisplayNodeController.update(node.getSubject());
    }

    @Override
    public void createTooltip(ControlNode<ActiveCard> n) {

    }

    @Override
    public void createContextMenu(ControlNode<ActiveCard> n) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem retrieveItem = new MenuItem("Retrieve");
        retrieveItem.setOnAction(actionEvent -> retrieveCard(n.getSubject()));
        contextMenu.getItems().add(retrieveItem);
        n.setOnContextMenuRequested(e -> contextMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    private void retrieveCard(ActiveCard c) {
        teamDisplayNodeController.returnCard(c);
        ownGridController.removeTarget(c);
    }

    @Override
    public void setMouseEvents(ControlNode<ActiveCard> displayControlNode) {

    }

    public void initialize(MainDatabase d, TeamDisplayNodeController tController,
                           GridDisplayController<ActiveCard> ownController) {
        mainDatabase = d;
        teamDisplayNodeController = tController;
        ownGridController = ownController;
    }
}
