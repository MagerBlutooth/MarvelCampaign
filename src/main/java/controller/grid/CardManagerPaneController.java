package controller.grid;

import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.thing.Card;
import model.thing.CardList;
import model.thing.ThingType;
import view.ViewSize;
import view.manager.CardManager;
import view.menu.CardFilterMenuButton;
import view.menu.CardSortMenuButton;
import view.node.control.ControlNode;
import view.pane.editor.CardEditorPane;


public class CardManagerPaneController extends ManagerPaneController<Card> {
    @FXML
    CardManager cardManager;
    @FXML
    CardSortMenuButton sortButton;
    @FXML
    CardFilterMenuButton filterButton;

    @Override
    public Scene getCurrentScene() {
        return cardManager.getScene();
    }

    @Override
    public void initialize(ControllerDatabase m) {
        super.initialize(m);
        CardList cards = new CardList(m.getCards());
        cardManager.initialize(cards, ThingType.CARD, this, ViewSize.MEDIUM, true);
        sortButton.initialize(cardManager.getListNodeController());
        filterButton.initialize(cardManager.getListNodeController());
    }

    @Override
    public void saveGridNode(ControlNode<Card> node) {
        controllerDatabase.saveDatabase(node.getSubject().getThingType());
    }

    @Override
    public void editSubject(ControlNode<Card> node) {
        CardEditorPane cardEditorPane = new CardEditorPane();
        cardEditorPane.initialize(controllerDatabase, ViewSize.LARGE, (Card)node.getSubject());
        changeScene(cardEditorPane);
    }

    @FXML
    public void addNewEntry()
    {
        CardEditorPane cardEditorPane = new CardEditorPane();
        cardEditorPane.initialize(controllerDatabase, ViewSize.LARGE, new Card());
        changeScene(cardEditorPane);
    }
}
