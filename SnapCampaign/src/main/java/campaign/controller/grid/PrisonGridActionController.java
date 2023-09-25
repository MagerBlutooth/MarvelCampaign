package campaign.controller.grid;

import campaign.controller.ControllerDatabase;
import campaign.controller.node.FactionSelectNodeController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import campaign.model.thing.Card;
import campaign.view.node.control.ControlNode;

public class PrisonGridActionController extends ThingActionController<Card> {

    FactionSelectNodeController factionController;

    public void initialize(ControllerDatabase database, DraggableThingDisplayController<Card> prisonDisplay, FactionSelectNodeController c)
    {
        super.initialize(database, prisonDisplay);
        factionController = c;
    }

    @Override
    public void createTooltip(ControlNode<Card> n) {

    }

    @Override
    public void createContextMenu(ControlNode<Card> n) {

        ContextMenu contextMenu = new ContextMenu();
        MenuItem reviveItem = new MenuItem("Revive");
        reviveItem.setOnAction(e -> factionController.reviveAgent(n.getSubject()));
        contextMenu.getItems().add(reviveItem);
        n.setOnContextMenuRequested(e -> contextMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<Card> displayControlNode) {

    }
}
