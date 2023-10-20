package snapMain.controller.grid;

import snapMain.controller.MainDatabase;
import snapMain.controller.node.FactionSelectNodeController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import snapMain.model.target.Card;
import snapMain.model.target.Location;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class PrisonGridActionController extends ThingActionController<Card> {

    FactionSelectNodeController factionController;

    public void initialize(MainDatabase database, DraggableThingDisplayController<Card> prisonDisplay, FactionSelectNodeController c)
    {
        super.initialize(database, prisonDisplay);
        factionController = c;
    }

    @Override
    public ControlNode<Card> createEmptyNode(ViewSize v) {
        ControlNode<Card> cardNode = new ControlNode<>();
        cardNode.initialize(mainDatabase, new Card(), mainDatabase.grabBlankImage(TargetType.LOCATION),
                v,false);
        return cardNode;
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
