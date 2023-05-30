package view.dialog;

import controller.ControllerDatabase;
import controller.grid.DialogGridActionController;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.stage.Modality;
import model.thing.*;
import view.ViewSize;
import view.fxml.FXMLGrabber;
import view.node.control.ControlNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            setResultConverter(buttonType -> {
                if(!Objects.equals(ButtonBar.ButtonData.OK_DONE, buttonType.getButtonData())) {
                    return selection;
                }

                return null;
            });
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
