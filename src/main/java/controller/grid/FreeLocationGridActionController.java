package controller.grid;

import controller.ControllerDatabase;
import controller.node.FreeAgentSelectNodeController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import model.thing.Card;
import model.thing.Location;
import view.IconImage;
import view.ViewSize;
import view.node.control.ControlNode;

public class FreeLocationGridActionController extends ThingActionController<Location> {

    ControllerDatabase controllerDatabase;
    FreeAgentSelectNodeController freeAgentController;
    String shieldName;
    String hydraName;

    @Override
    public ControlNode<Location> createControlNode(Location l, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Location> node = new ControlNode<>();
        node.initialize(controllerDatabase, l, i, v, blind);
        //createTooltip(node);
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
    public void createContextMenu(ControlNode<Location> n) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem shieldItem = new MenuItem("To"+shieldName);
        shieldItem.setOnAction(e -> freeAgentController.toShield(n.getSubject()));
        MenuItem hydraItem = new MenuItem("To"+hydraName);
        hydraItem.setOnAction(e -> freeAgentController.toHydra(n.getSubject()));
        MenuItem ruinItem = new MenuItem();
        if (n.getSubject().isRuined())
            ruinItem.setText("Repair");
        else
            ruinItem.setText("Ruin");
        ruinItem.setOnAction(e -> freeAgentController.toggleRuinLocation(n.getSubject()));
        contextMenu.getItems().add(shieldItem);
        contextMenu.getItems().add(hydraItem);
        contextMenu.getItems().add(ruinItem);
        n.setOnContextMenuRequested(e -> contextMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<Location> displayControlNode) {

    }
}
