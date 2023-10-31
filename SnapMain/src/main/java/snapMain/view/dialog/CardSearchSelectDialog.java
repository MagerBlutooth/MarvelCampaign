package snapMain.view.dialog;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.DialogGridActionController;
import snapMain.model.target.*;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;

import java.util.ArrayList;

public class CardSearchSelectDialog extends SearchSelectDialog<Card> {

    @Override
    public void initialize(MainDatabase cd, TargetList<Card> selectableCards)
    {
        super.initialize(cd, selectableCards);

        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            CardList cards = new CardList(new ArrayList<>());
            for(Card c: selectableCards)
            {
                String name = c.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if(name.contains(searchString))
                    cards.add(c);
            }
            DialogGridActionController<Card> gridController = new DialogGridActionController<>();
            gridController.initialize(cd, this);
            choiceNodes.initialize(cards, TargetType.CARD, gridController, ViewSize.MEDIUM, false);
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
        viewNode.initialize(mainDatabase, c, mainDatabase.grabImage(c), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        selection = c;
    }
}
