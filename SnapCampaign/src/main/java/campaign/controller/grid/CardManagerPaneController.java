package campaign.controller.grid;

import campaign.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import campaign.model.thing.Card;
import campaign.model.thing.CardList;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import campaign.view.manager.CardManager;
import campaign.view.menu.CardFilterMenuButton;
import campaign.view.menu.CardSortMenuButton;
import campaign.view.node.control.ControlNode;
import campaign.view.pane.editor.CardEditorPane;


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
        cardManager.initialize(cards, ThingType.CARD, this, ViewSize.MEDIUM, false);
        sortButton.initialize(cardManager.getListNodeController());
        filterButton.initialize(cardManager.getListNodeController());
    }

    @Override
    public void saveGridNode(ControlNode<Card> node) {
        mainDatabase.saveDatabase(node.getSubject().getThingType());
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
