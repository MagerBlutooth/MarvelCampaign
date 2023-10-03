package snapMain.controller.grid;

import snapMain.controller.MainDatabase;
import snapMain.controller.node.FactionSelectNodeController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import snapMain.model.target.Card;
import snapMain.view.dialog.CardSelectDialog;
import snapMain.view.node.control.ControlNode;
import snapMain.view.node.control.DraggableControlNode;

import java.util.Optional;

public class AgentGridActionController extends ThingActionController<Card> {

    FactionSelectNodeController factionController;

    public void initialize(MainDatabase database, DraggableThingDisplayController<Card> agentDisplay,
                           FactionSelectNodeController c) {
        super.initialize(database, agentDisplay);
        factionController = c;
    }

    @Override
    public void createContextMenu(ControlNode<Card> node) {
        ContextMenu contextMenu = new ContextMenu();
        Card card = node.getSubject();
        MenuItem woundItem = new MenuItem();
        woundItem.setOnAction(actionEvent ->
        {
            node.toggle();
            saveGridNode(node);
        });
        contextMenu.getItems().add(woundItem);
        if (card.isWounded()) {
            woundItem.setText("Heal");
            MenuItem eliminateItem = new MenuItem("Eliminate");
            eliminateItem.setOnAction(actionEvent ->
            {
                factionController.eliminateAgent(card);
            });
            contextMenu.getItems().add(eliminateItem);
        } else
            woundItem.setText("Wound");
        MenuItem captainItem = new MenuItem("Captain");
        captainItem.setOnAction(actionEvent -> {
            if (node instanceof DraggableControlNode) {
                card.setCaptain(!card.isCaptain());
                saveGridNode(node);
            }
        });
        contextMenu.getItems().add(captainItem);
        MenuItem addCardItem = new MenuItem("Add Card");
        addCardItem.setOnAction(e -> {
            CardSelectDialog dialog = new CardSelectDialog();
            dialog.initialize(mainDatabase);
            Optional<Card> newCard = dialog.showAndWait();
            newCard.ifPresent(value -> factionController.addAgent(value));
        });
        MenuItem deleteCardItem = new MenuItem("Delete Card");
        deleteCardItem.setOnAction(actionEvent -> {
            if (node instanceof DraggableControlNode) {
                factionController.removeAgent(card);
            }
        });
        contextMenu.getItems().add(addCardItem);
        contextMenu.getItems().add(deleteCardItem);
        node.setOnContextMenuRequested(e -> contextMenu.show(node, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<Card> displayControlNode) {

    }
}
