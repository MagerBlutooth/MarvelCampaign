package adventure.controller;

import adventure.model.target.ActiveCard;
import adventure.view.node.ActiveCardControlNode;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.logger.MLogger;
import snapMain.model.target.StatusEffect;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.grabber.IconConstant;
import snapMain.view.node.control.ControlNode;

public class TeamGridActionController implements GridActionController<ActiveCard> {

    MainDatabase mainDatabase;
    TeamDisplayNodeController teamDisplayNodeController;

    @Override
    public ControlNode<ActiveCard> createControlNode(ActiveCard card, IconImage i, ViewSize v, boolean statusVisible) {
        ActiveCardControlNode node = new ActiveCardControlNode();
        node.initialize(getDatabase(), card, i, v, statusVisible);
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

    private void setGraphic(MenuItem item, ImageView image)
    {
        image.setFitWidth(20);
        image.setFitHeight(20);
        item.setGraphic(image);
    }

    @Override
    public void createContextMenu(ControlNode<ActiveCard> n) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem woundItem = setWoundItem(n);
        MenuItem eliminateItem = new MenuItem("Eliminate");
        MenuItem captureItem = new MenuItem("Capture");
        MenuItem miaItem = new MenuItem("Send Away");
        MenuItem tempItem = new MenuItem("To Temp");
        MenuItem defectItem = new MenuItem("Defect");
        MenuItem captainItem = new MenuItem("Toggle Captain");
        MenuItem pigItem = new MenuItem("Pig");
        MenuItem raptorItem = new MenuItem(("Raptor"));

        setGraphic(captureItem, new ImageView(mainDatabase.grabIcon(IconConstant.CAPTURE)));
        setGraphic(eliminateItem, new ImageView(mainDatabase.grabIcon(IconConstant.ELIMINATE)));
        setGraphic(captainItem, new ImageView(mainDatabase.grabIcon(IconConstant.STAR)));
        setGraphic(defectItem, new ImageView(mainDatabase.grabIcon(IconConstant.DEFECT)));
        setGraphic(miaItem, new ImageView(mainDatabase.grabIcon(IconConstant.SEND_AWAY)));
        setGraphic(tempItem, new ImageView(mainDatabase.grabIcon(IconConstant.TEMP)));
        setGraphic(pigItem, new ImageView(mainDatabase.grabIcon(IconConstant.PIG)));
        setGraphic(raptorItem, new ImageView(mainDatabase.grabIcon(IconConstant.RAPTOR)));
        eliminateItem.setOnAction(actionEvent -> teamDisplayNodeController.eliminate(n.getSubject()));
        captureItem.setOnAction(actionEvent -> teamDisplayNodeController.capture(n.getSubject()));
        miaItem.setOnAction(actionEvent -> teamDisplayNodeController.sendAway(n.getSubject()));
        tempItem.setOnAction(actionEvent -> teamDisplayNodeController.teamToTemp(n.getSubject()));
        captainItem.setOnAction(actionEvent -> {
            teamDisplayNodeController.toggleCaptain(n.getSubject());
            createContextMenu(n);
        });
        pigItem.setOnAction(actionEvent -> {
            teamDisplayNodeController.togglePig(n.getSubject());
            createContextMenu(n);
        });
        raptorItem.setOnAction(actionEvent -> {
            teamDisplayNodeController.toggleRaptor(n.getSubject());
            createContextMenu(n);
        });
        defectItem.setOnAction(actionEvent -> teamDisplayNodeController.makeCardFreeAgent(n.getSubject()));
        contextMenu.getItems().add(woundItem);
        contextMenu.getItems().add(eliminateItem);
        contextMenu.getItems().add(captureItem);
        contextMenu.getItems().add(miaItem);
        contextMenu.getItems().add(tempItem);
        contextMenu.getItems().add(captainItem);
        contextMenu.getItems().add(defectItem);
        contextMenu.getItems().add(pigItem);
        contextMenu.getItems().add(raptorItem);
        n.setOnContextMenuRequested(e -> contextMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    private MenuItem setWoundItem(ControlNode<ActiveCard> n) {
        ActiveCard c = n.getSubject();
        MenuItem woundItem = new MenuItem();
        if(c.hasStatus(StatusEffect.WOUND)) {
            woundItem.setText("Heal");
            setGraphic(woundItem, new ImageView(mainDatabase.grabIcon(IconConstant.HEAL)));
        }
        else {
            woundItem.setText("Wound");
            setGraphic(woundItem, new ImageView(mainDatabase.grabIcon(IconConstant.WOUND)));
        }

        woundItem.setOnAction(actionEvent -> {
            teamDisplayNodeController.toggleWound(c);
            setWoundItem(n);
        });
        return woundItem;
    }

    @Override
    public void setMouseEvents(ControlNode<ActiveCard> displayControlNode) {

    }

    public void initialize(MainDatabase d, TeamDisplayNodeController tController) {
        mainDatabase = d;
        teamDisplayNodeController = tController;
    }
}
