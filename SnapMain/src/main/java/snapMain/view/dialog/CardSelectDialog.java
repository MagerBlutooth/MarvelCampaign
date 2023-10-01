package snapMain.view.dialog;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.DialogGridActionController;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import snapMain.model.thing.Card;
import snapMain.model.thing.CardList;
import snapMain.model.thing.EffectBaseObject;
import snapMain.model.thing.TargetType;

import java.util.ArrayList;
import java.util.List;

public class CardSelectDialog extends SelectDialog<Card> {

    @Override
    public void initialize(MainDatabase cd)
    {
        super.initialize(cd);
        List<Card> allCards = mainDatabase.getCards();

        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            CardList cards = new CardList(new ArrayList<>());
            for(Card c: allCards)
            {
                String name = c.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if(name.contains(searchString))
                    cards.add(c);
            }
            DialogGridActionController<Card> gridController = new DialogGridActionController<>();
            gridController.intialize(cd, this);
            choices.initialize(cards, TargetType.CARD, gridController, ViewSize.MEDIUM, false);
    });

        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return selection;
            }
            return null;
        });
    }

    @Override
    public void setChoice(Card c) {
        displayPane.getChildren().clear();
        ControlNode<EffectBaseObject> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, c, mainDatabase.grabImage(c, c.getTargetType()), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        selection = c;
    }
}
