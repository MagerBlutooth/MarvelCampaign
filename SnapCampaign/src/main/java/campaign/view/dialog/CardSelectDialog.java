package campaign.view.dialog;

import campaign.controller.MainDatabase;
import campaign.controller.grid.DialogGridActionController;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import campaign.model.thing.Card;
import campaign.model.thing.CardList;
import campaign.model.thing.EffectThing;
import campaign.model.thing.ThingType;

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
            choices.initialize(cards, ThingType.CARD, gridController, ViewSize.MEDIUM, false);
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
        ControlNode<EffectThing> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, c, mainDatabase.grabImage(c, c.getThingType()), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        selection = c;
    }
}
