package adventure.controller.manager;

import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvCard;
import adventure.model.thing.AdvCardList;
import adventure.view.manager.BossManager;
import adventure.view.node.AdvCardControlNode;
import adventure.view.pane.AdvEditorMenuPane;
import adventure.view.pane.BossEditorPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.ManagerPaneController;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;


public class AdvCardManagerPaneController extends ManagerPaneController<AdvCard, AdvMainDatabase> implements GridActionController<AdvCard> {
    @FXML
    BossManager bossManager;

    @Override
    public void initializeButtonToolBar() {

        AdvEditorMenuPane menuPane = new AdvEditorMenuPane();
        menuPane.initialize(mainDatabase);
        buttonToolBar.initialize(menuPane);
    }

    @Override
    public Scene getCurrentScene() {
        return bossManager.getScene();
    }

    @Override
    public void initialize(AdvMainDatabase m) {
        super.initialize(m);
        AdvCardList cards = new AdvCardList(m.getBosses());
        /*When creating the card manager, automatically set all cards to not be captains to avoid the issue
        //of cards getting set as such from the most recent campaign*/
        bossManager.initialize(cards, TargetType.CARD, this, ViewSize.MEDIUM, true);
    }

    @Override
    public void editSubject(ControlNode<AdvCard> node) {
        BossEditorPane bossEditorPane = new BossEditorPane();
        AdvCard b = node.getSubject();
        bossEditorPane.initialize(mainDatabase, b);
        changeScene(bossEditorPane);
    }

    @Override
    public ControlNode<AdvCard> createControlNode(AdvCard c, IconImage i, ViewSize v, boolean revealed) {
        AdvCardControlNode node = new AdvCardControlNode();
        node.initialize(mainDatabase, c, i, v, revealed);
        createTooltip(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public void saveGridNode(ControlNode<AdvCard> node) {

    }

    @Override
    public void createTooltip(ControlNode<AdvCard> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(actionEvent -> editSubject(n));
        rightClickMenu.getItems().add(editMenuItem);
        n.setOnContextMenuRequested(e -> rightClickMenu.show(n, e.getScreenX(), e.getScreenY()));
    }
    @Override
    public void createContextMenu(ControlNode<AdvCard> n) {

    }

    @Override
    public void setMouseEvents(ControlNode<AdvCard> controlNode) {
        AdvCard advCard = controlNode.getSubject();
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mainDatabase.toggleBoss(advCard.getCard());
                mainDatabase.saveDatabase(TargetType.CARD);
                controlNode.toggle();
            }});

    }
}
