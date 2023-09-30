package campaign.controller.grid;

import campaign.controller.MainDatabase;
import campaign.controller.node.FreeAgentSelectNodeController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import campaign.model.thing.Card;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;

public class FreeAgentGridActionController extends ThingActionController<Card> {

    MainDatabase mainDatabase;
    FreeAgentSelectNodeController freeAgentController;
    String shieldName;
    String hydraName;

    @Override
    public ControlNode<Card> createControlNode(Card c, IconImage i, ViewSize v, boolean blind) {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(mainDatabase, c, i, v, blind);
        createTooltip(node);
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    public void initialize(MainDatabase database, FreeAgentSelectNodeController controller, String s, String h)
    {
        mainDatabase = database;
        freeAgentController = controller;
        shieldName = s;
        hydraName = h;
    }

    @Override
    public MainDatabase getDatabase() {
        return mainDatabase;
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
