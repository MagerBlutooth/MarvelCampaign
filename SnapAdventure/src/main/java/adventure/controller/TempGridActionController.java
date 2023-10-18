package adventure.controller;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.Card;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.grabber.IconConstant;
import snapMain.view.node.control.ControlNode;

public class TempGridActionController implements GridActionController<Card> {

    MainDatabase mainDatabase;
    TeamDisplayNodeController teamDisplayNodeController;

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
        MenuItem defectItem = new MenuItem("Defect");
        defectItem.setOnAction(actionEvent -> teamDisplayNodeController.makeCardFreeAgent(n.getSubject()));
        MenuItem teamItem = new MenuItem("To Team");
        teamItem.setOnAction(actionEvent -> teamDisplayNodeController.fromTempToTeam(n.getSubject()));
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
    public void setMouseEvents(ControlNode<Card> displayControlNode) {

    }

    public void initialize(MainDatabase d, TeamDisplayNodeController tController) {
        mainDatabase = d;
        teamDisplayNodeController = tController;
    }
}
