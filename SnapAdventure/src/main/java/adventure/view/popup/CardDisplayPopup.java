package adventure.view.popup;

import javafx.scene.control.ScrollPane;
import snapMain.controller.grid.GridActionController;
import snapMain.model.thing.Card;
import snapMain.model.thing.CardList;
import snapMain.model.thing.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import javafx.geometry.Point2D;
import javafx.scene.Scene;

public class CardDisplayPopup extends AdvPopup {

    public CardDisplayPopup(CardList cards, Point2D p, GridActionController<Card> controller)
    {
        GridDisplayNode<Card> cardDisplay = new GridDisplayNode<>();
        cardDisplay.initialize(cards, TargetType.CARD, controller, ViewSize.TINY, false);
        cardDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        setX(p.getX());
        setY(p.getY());
        Scene scene = new Scene(cardDisplay);
        setScene(scene);
    }
}
