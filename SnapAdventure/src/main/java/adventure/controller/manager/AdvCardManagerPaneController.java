package adventure.controller.manager;

import adventure.model.AdvMainDatabase;
import adventure.model.target.base.AdvCard;
import adventure.model.target.base.AdvCardList;
import adventure.view.manager.BossManager;
import adventure.view.node.AdvCardControlNode;
import adventure.view.pane.AdvCardEditorPane;
import adventure.view.pane.AdvEditorMenuPane;
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
import snapMain.view.menu.FilterMenuButton;
import snapMain.view.menu.SortMenuButton;
import snapMain.view.node.control.ControlNode;


public class AdvCardManagerPaneController extends ManagerPaneController<AdvCard, AdvMainDatabase> implements GridActionController<AdvCard> {
    @FXML
    BossManager bossManager;
    @FXML
    SortMenuButton<AdvCard> sortButton;
    @FXML
    FilterMenuButton<AdvCard> filterButton;

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
        AdvCardList cards = new AdvCardList(m.getActualAdvCards());
        bossManager.initialize(cards, TargetType.CARD, this, ViewSize.MEDIUM, true);
        sortButton.initialize(bossManager.getListNodeController());
        filterButton.initialize(bossManager.getListNodeController());
    }

    @Override
    public void editSubject(ControlNode<AdvCard> node) {
        AdvCardEditorPane advCardEditorPane = new AdvCardEditorPane();
        AdvCard b = node.getSubject();
        advCardEditorPane.initialize(mainDatabase, b);
        changeScene(advCardEditorPane);
    }

    @Override
    public ControlNode<AdvCard> createControlNode(AdvCard c, IconImage i, ViewSize v, boolean revealed) {
        AdvCardControlNode node = new AdvCardControlNode();
        node.initialize(mainDatabase, c, i, v, revealed);
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public ControlNode<AdvCard> createEmptyNode(ViewSize v) {
        ControlNode<AdvCard> cardNode = new ControlNode<>();
        cardNode.initialize(mainDatabase, new AdvCard(), mainDatabase.grabBlankImage(TargetType.CARD),
                v,false);
        return cardNode;
    }

    @Override
    public void saveGridNode(ControlNode<AdvCard> node) {

    }

    @Override
    public void createTooltip(ControlNode<AdvCard> n) {

    }
    @Override
    public void createContextMenu(ControlNode<AdvCard> n) {
    }

    @Override
    public void setMouseEvents(ControlNode<AdvCard> controlNode) {
        AdvCard advCard = controlNode.getSubject();
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mainDatabase.toggleAdvCard(advCard.getCard());
                mainDatabase.saveDatabase(TargetType.CARD);
                controlNode.toggle();
            }});

    }
}
