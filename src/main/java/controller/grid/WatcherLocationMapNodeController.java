package controller.grid;

import controller.ControllerDatabase;
import controller.ScrollSetup;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import model.thing.Card;
import model.thing.Faction;
import model.thing.Location;
import model.thing.ThingType;
import view.IconImage;
import view.ViewSize;
import view.dialog.CardSelectDialog;
import view.node.DroppableLocationDisplayNode;
import view.node.LocationMapNode;
import view.node.control.ControlNode;
import view.node.control.DraggableMapControlNode;

import java.util.List;
import java.util.Optional;

public class WatcherLocationMapNodeController extends LocationMapNodeController {

    @Override
    public void createContextMenu(ControlNode<Location> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        MenuItem addCardItem = new MenuItem("Add Card");
        addCardItem.setOnAction(e -> {
            CardSelectDialog dialog = new CardSelectDialog();
            dialog.initialize(controllerDatabase);
            Optional<Card> card = dialog.showAndWait();
            if(card.isPresent() && !n.getSubject().isFull())
                n.getSubject().stationAgent(card.get());
            refresh();
        });
        rightClickMenu.getItems().add(addCardItem);
        n.setOnContextMenuRequested(e -> rightClickMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public ControlNode<Location> createControlNode(Location l, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Location> n = super.createControlNode(l, i, v, blind);
        createContextMenu(n);
        return n;
    }
}
