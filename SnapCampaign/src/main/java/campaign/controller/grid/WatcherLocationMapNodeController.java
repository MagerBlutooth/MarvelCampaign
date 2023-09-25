package campaign.controller.grid;

import campaign.controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import campaign.model.thing.Card;
import campaign.model.thing.Faction;
import campaign.model.thing.Location;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.dialog.CardSelectDialog;
import campaign.view.node.control.ControlNode;

import java.util.Optional;

public class WatcherLocationMapNodeController extends LocationMapNodeController {

    @FXML
    public TextField influenceVal;

    @Override
    public void initialize(ControllerDatabase d, Faction f, boolean blind)
    {
        super.initialize(d, f, blind);
        influenceVal.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")){
                influenceVal.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @Override
    public void createContextMenu(ControlNode<Location> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        MenuItem addCardItem = new MenuItem("Add Card");
        addCardItem.setOnAction(e -> {
            CardSelectDialog dialog = new CardSelectDialog();
            dialog.initialize(controllerDatabase);
            Optional<Card> card = dialog.showAndWait();
            Location l = n.getSubject();
            if(card.isPresent() && !l.isFull())
                l.stationAgent(card.get());
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

    public int getInfluence() {
        return Integer.parseInt(influenceVal.getText());
    }
}
