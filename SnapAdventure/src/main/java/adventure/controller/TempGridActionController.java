package adventure.controller;

import adventure.model.target.ActiveCard;
import adventure.view.node.ActiveCardControlNode;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.logger.MLogger;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.grabber.IconConstant;
import snapMain.view.node.control.ControlNode;

public class TempGridActionController implements GridActionController<ActiveCard> {

    MainDatabase mainDatabase;
    TeamDisplayNodeController teamDisplayNodeController;

    final static MLogger logger = new MLogger(TempGridActionController.class);

    @Override
    public ControlNode<ActiveCard> createControlNode(ActiveCard card, IconImage i, ViewSize v, boolean blind) {
        ActiveCardControlNode node = new ActiveCardControlNode();
        node.initialize(getDatabase(), card, i, v, blind);
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
        MenuItem defectItem = new MenuItem("Defect");
        defectItem.setOnAction(actionEvent -> {
            teamDisplayNodeController.makeCardFreeAgent(n.getSubject());
            logger.info(n.getSubject()+ " defected to the enemy team.");
        });
        MenuItem teamItem = new MenuItem("To Team");
        teamItem.setOnAction(actionEvent -> {
            teamDisplayNodeController.fromTempToTeam(n.getSubject());
            logger.info(n.getSubject()+ " moved to main team.");
        });
        setGraphic(defectItem, new ImageView(mainDatabase.grabIcon(IconConstant.DEFECT)));
        setGraphic(teamItem, new ImageView(mainDatabase.grabIcon(IconConstant.TEAM)));
        contextMenu.getItems().add(teamItem);
        contextMenu.getItems().add(defectItem);
        n.setOnContextMenuRequested(e -> contextMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    private void setGraphic(MenuItem item, ImageView image)
    {
        image.setFitWidth(20);
        image.setFitHeight(20);
        item.setGraphic(image);
    }

    @Override
    public void setMouseEvents(ControlNode<ActiveCard> displayControlNode) {

    }

    public void initialize(MainDatabase d, TeamDisplayNodeController tController) {
        mainDatabase = d;
        teamDisplayNodeController = tController;
    }
}
