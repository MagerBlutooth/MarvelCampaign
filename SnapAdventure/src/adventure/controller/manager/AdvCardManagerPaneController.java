package adventure.controller.manager;

import adventure.model.AdvControllerDatabase;
import adventure.model.Boss;
import adventure.view.node.BossControlNode;
import adventure.view.pane.AdvCardEditorPane;
import adventure.view.pane.AdvEditMenuPane;
import campaign.controller.grid.GridActionController;
import campaign.controller.grid.ManagerPaneController;
import campaign.model.thing.Card;
import campaign.model.thing.CardList;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.manager.CardManager;
import campaign.view.menu.CardFilterMenuButton;
import campaign.view.menu.CardSortMenuButton;
import campaign.view.node.control.ControlNode;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class AdvCardManagerPaneController extends ManagerPaneController<Card, AdvControllerDatabase> implements GridActionController<Card> {
    @FXML
    CardManager cardManager;
    @FXML
    CardSortMenuButton sortButton;
    @FXML
    CardFilterMenuButton filterButton;

    @FXML
    public void goBack()
    {
        AdvEditMenuPane menuPane = new AdvEditMenuPane();
        menuPane.initialize(controllerDatabase);
        changeScene(menuPane);
    }

    @Override
    public Scene getCurrentScene() {
        return cardManager.getScene();
    }

    @Override
    public void initialize(AdvControllerDatabase m) {
        super.initialize(m);
        CardList cards = new CardList(m.getCards());
        /*When creating the card manager, automatically set all cards to not be captains to avoid the issue
        //of cards getting set as such from the most recent campaign*/
        for(Card c: cards)
        {
            c.setCaptain(false);
        }
        cardManager.initialize(cards, ThingType.CARD, this, ViewSize.MEDIUM, false);
        sortButton.initialize(cardManager.getListNodeController());
        filterButton.initialize(cardManager.getListNodeController());
    }

    @Override
    public void editSubject(ControlNode<Card> node) {
        AdvCardEditorPane cardEditorPane = new AdvCardEditorPane();
        cardEditorPane.initialize(controllerDatabase, ViewSize.LARGE, node.getSubject());
        changeScene(cardEditorPane);
    }

    @Override
    public ControlNode<Card> createControlNode(Card c, IconImage i, ViewSize v, boolean blind) {
        BossControlNode node = new BossControlNode();
        node.initialize(controllerDatabase, c, i, v, blind);
        createTooltip(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public void saveGridNode(ControlNode<Card> node) {

    }

    @Override
    public void createTooltip(ControlNode<Card> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(actionEvent -> editSubject(n));
        rightClickMenu.getItems().add(editMenuItem);
        n.setOnContextMenuRequested(e -> rightClickMenu.show(n, e.getScreenX(), e.getScreenY()));
    }


    public void editMenuItem(Card card)
    {
        AdvCardEditorPane cardEditorPane = new AdvCardEditorPane();
        cardEditorPane.initialize(controllerDatabase, ViewSize.MEDIUM, card);
        changeScene(cardEditorPane);
    }
    @Override
    public void createContextMenu(ControlNode<Card> n) {

    }

    @Override
    public void setMouseEvents(ControlNode<Card> controlNode) {
        Card card = controlNode.getSubject();
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                controllerDatabase.toggleBoss(card);
                controllerDatabase.saveDatabase(ThingType.CARD);
                controlNode.toggle();
            }});

    }
}
