package controller.grid;

import controller.ControllerDatabase;
import controller.node.FreeAgentSelectNodeController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import model.thing.Card;
import model.thing.Faction;
import model.thing.Thing;
import view.IconImage;
import view.ViewSize;
import view.node.control.ControlNode;
import view.node.control.DraggableControlNode;

public class FreeAgentGridActionController extends ThingActionController<Card> {

    ControllerDatabase controllerDatabase;
    FreeAgentSelectNodeController freeAgentController;
    String shieldName;
    String hydraName;

    @Override
    public ControlNode<Card> createControlNode(Card c, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(controllerDatabase, c, i, v, blind);
        createTooltip(node);
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    public void initialize(ControllerDatabase database, FreeAgentSelectNodeController controller, String s, String h)
    {
        controllerDatabase = database;
        freeAgentController = controller;
        shieldName = s;
        hydraName = h;
    }

    @Override
    public ControllerDatabase getDatabase() {
        return controllerDatabase;
    }

    @Override
    public void createContextMenu(ControlNode<Card> n) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem shieldItem = new MenuItem("To "+shieldName);
        shieldItem.setOnAction(e -> freeAgentController.toShield(n.getSubject()));
        MenuItem hydraItem = new MenuItem("To "+hydraName);
        hydraItem.setOnAction(e -> freeAgentController.toHydra(n.getSubject()));
        Card card = n.getSubject();
        MenuItem woundItem = new MenuItem();
        woundItem.setOnAction(e -> freeAgentController.toggleWoundAgent(card));
        if (card.isWounded()) {
            woundItem.setText("Heal");
            MenuItem eliminateItem = new MenuItem("Eliminate");
            eliminateItem.setOnAction(actionEvent ->
            {
                freeAgentController.eliminateAgent(card);
            });
            contextMenu.getItems().add(eliminateItem);
        }
        else
            woundItem.setText("Wound");

        MenuItem addItem = new MenuItem("Add Card");
        addItem.setOnAction(e -> freeAgentController.addAgent(card));
        MenuItem deleteItem = new MenuItem("Delete Card");
        deleteItem.setOnAction(e -> freeAgentController.removeAgent(card));
        contextMenu.getItems().add(woundItem);
        contextMenu.getItems().add(shieldItem);
        contextMenu.getItems().add(hydraItem);
        contextMenu.getItems().add(addItem);
        contextMenu.getItems().add(deleteItem);

        n.setOnContextMenuRequested(e -> contextMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<Card> displayControlNode) {

    }
}
