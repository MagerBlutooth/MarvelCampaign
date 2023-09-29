package adventure.controller.manager;

import adventure.model.AdvControllerDatabase;
import adventure.model.Boss;
import adventure.model.BossList;
import adventure.view.manager.BossManager;
import adventure.view.node.BossControlNode;
import adventure.view.pane.BossEditorPane;
import adventure.view.pane.AdvEditorMenuPane;
import campaign.controller.grid.GridActionController;
import campaign.controller.grid.ManagerPaneController;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.menu.CardFilterMenuButton;
import campaign.view.menu.CardSortMenuButton;
import campaign.view.node.control.ControlNode;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class AdvBossManagerPaneController extends ManagerPaneController<Boss, AdvControllerDatabase> implements GridActionController<Boss> {
    @FXML
    BossManager bossManager;

    @Override
    public void initializeButtonToolBar() {

        AdvEditorMenuPane menuPane = new AdvEditorMenuPane();
        menuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(menuPane);
    }

    @Override
    public Scene getCurrentScene() {
        return bossManager.getScene();
    }

    @Override
    public void initialize(AdvControllerDatabase m) {
        super.initialize(m);
        BossList cards = new BossList(m.getBosses());
        /*When creating the card manager, automatically set all cards to not be captains to avoid the issue
        //of cards getting set as such from the most recent campaign*/
        bossManager.initialize(cards, ThingType.CARD, this, ViewSize.MEDIUM, true);
    }

    @Override
    public void editSubject(ControlNode<Boss> node) {
        BossEditorPane bossEditorPane = new BossEditorPane();
        Boss b = node.getSubject();
        bossEditorPane.initialize(controllerDatabase, b);
        changeScene(bossEditorPane);
    }

    @Override
    public ControlNode<Boss> createControlNode(Boss c, IconImage i, ViewSize v, boolean revealed) {
        BossControlNode node = new BossControlNode();
        node.initialize(controllerDatabase, c, i, v, revealed);
        createTooltip(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public void saveGridNode(ControlNode<Boss> node) {

    }

    @Override
    public void createTooltip(ControlNode<Boss> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(actionEvent -> editSubject(n));
        rightClickMenu.getItems().add(editMenuItem);
        n.setOnContextMenuRequested(e -> rightClickMenu.show(n, e.getScreenX(), e.getScreenY()));
    }
    @Override
    public void createContextMenu(ControlNode<Boss> n) {

    }

    @Override
    public void setMouseEvents(ControlNode<Boss> controlNode) {
        Boss boss = controlNode.getSubject();
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                controllerDatabase.toggleBoss(boss.getCard());
                controllerDatabase.saveDatabase(ThingType.CARD);
                controlNode.toggle();
            }});

    }
}
