package controller.grid;

import controller.ControllerDatabase;
import controller.node.FreeAgentSelectNodeController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import model.thing.Location;
import view.IconImage;
import view.ViewSize;
import view.dialog.LocationSelectDialog;
import view.node.GridDisplayNode;
import view.node.control.ControlNode;

import java.util.Optional;

public class FreeLocationGridActionController extends ThingActionController<Location> {
    ControllerDatabase controllerDatabase;
    FreeAgentSelectNodeController freeAgentController;
    String shieldName;
    String hydraName;
    GridDisplayNode<Location> locationGridDisplayNode;

    @Override
    public ControlNode<Location> createControlNode(Location l, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Location> node = new ControlNode<>();
        node.initialize(controllerDatabase, l, i, v, blind);
        //createTooltip(node);
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    public void initialize(ControllerDatabase database, FreeAgentSelectNodeController controller, String s, String h,
                           GridDisplayNode<Location> locDisplay)
    {
        controllerDatabase = database;
        freeAgentController = controller;
        shieldName = s;
        hydraName = h;
        locationGridDisplayNode = locDisplay;
    }

    @Override
    public ControllerDatabase getDatabase() {
        return controllerDatabase;
    }

    @Override
    public void createContextMenu(ControlNode<Location> n) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem shieldItem = new MenuItem("To "+shieldName);
        shieldItem.setOnAction(e -> freeAgentController.toShield(n.getSubject()));
        MenuItem hydraItem = new MenuItem("To "+hydraName);
        hydraItem.setOnAction(e -> freeAgentController.toHydra(n.getSubject()));
        MenuItem ruinItem = new MenuItem();
        if (n.getSubject().isRuined())
            ruinItem.setText("Repair");
        else
            ruinItem.setText("Ruin");
        ruinItem.setOnAction(e -> freeAgentController.toggleRuinLocation(n.getSubject()));
        MenuItem addLocItem = new MenuItem("Add Location");
        addLocItem.setOnAction(e -> {
            LocationSelectDialog dialog = new LocationSelectDialog();
            dialog.initialize(controllerDatabase);
            Optional<Location> newLoc = dialog.showAndWait();
            newLoc.ifPresent(location -> locationGridDisplayNode.addThing(location));
            locationGridDisplayNode.sortBy("Name");
        });
        MenuItem delLocItem = new MenuItem("Delete");
        delLocItem.setOnAction(e -> {locationGridDisplayNode.removeThing(n.getSubject());
            locationGridDisplayNode.sortBy("Name");
        });
        contextMenu.getItems().add(shieldItem);
        contextMenu.getItems().add(hydraItem);
        contextMenu.getItems().add(ruinItem);
        contextMenu.getItems().add(addLocItem);
        contextMenu.getItems().add(delLocItem);
        n.setOnContextMenuRequested(e -> contextMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<Location> displayControlNode) {

    }
}
