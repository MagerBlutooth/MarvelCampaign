package view.dialog;

import controller.ControllerDatabase;
import controller.grid.DialogGridActionController;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import model.thing.Card;
import model.thing.CardList;
import model.thing.EffectThing;
import model.thing.ThingType;
import view.ViewSize;
import view.node.control.ControlNode;

import java.util.ArrayList;
import java.util.List;

public class CardSelectDialog extends SelectDialog<Card> {

    @Override
    public void initialize(ControllerDatabase cd)
    {
        super.initialize(cd);
        List<Card> allCards = controllerDatabase.getCards();

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
        viewNode.initialize(controllerDatabase, c,controllerDatabase.grabImage(c, c.getThingType()), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        selection = c;
    }
}
