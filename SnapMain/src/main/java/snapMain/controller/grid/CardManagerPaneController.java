package snapMain.controller.grid;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.manager.CardManager;
import snapMain.view.menu.CardFilterMenuButton;
import snapMain.view.menu.CardSortMenuButton;
import snapMain.view.node.control.ControlNode;
import snapMain.view.pane.editor.CardEditorPane;


public class CardManagerPaneController extends ManagerPaneController<Card, MainDatabase> {
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
    public void initialize(MainDatabase m) {
        super.initialize(m);
        CardList cards = new CardList(m.getCards());
        /*When creating the card manager, automatically set all cards to not be captains to avoid the issue
        //of cards getting set as such from the most recent campaign*/
        for(Card c: cards)
        {
            c.setCaptain(false);
        }
        cardManager.initialize(cards, TargetType.CARD, this, ViewSize.MEDIUM, false);
        sortButton.initialize(cardManager.getListNodeController());
        filterButton.initialize(cardManager.getListNodeController());
    }

    @Override
    public void saveGridNode(ControlNode<Card> node) {
        mainDatabase.saveDatabase(node.getSubject().getTargetType());
    }

    @Override
    public void editSubject(ControlNode<Card> node) {

        CardEditorPane cardEditorPane = new CardEditorPane();
        cardEditorPane.initialize(mainDatabase, ViewSize.LARGE, (Card)node.getSubject());
        changeScene(cardEditorPane);
    }

    @FXML
    public void addNewEntry()
    {
        CardEditorPane cardEditorPane = new CardEditorPane();
        cardEditorPane.initialize(mainDatabase, ViewSize.LARGE, new Card());
        changeScene(cardEditorPane);
    }
}
