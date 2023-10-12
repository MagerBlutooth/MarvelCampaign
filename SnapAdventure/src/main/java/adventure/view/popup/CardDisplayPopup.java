package adventure.view.popup;

import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvCard;
import adventure.view.node.AdvCardControlNode;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class CardDisplayPopup extends AdvPopup {

    public CardDisplayPopup(MainDatabase db, Card c, Point2D p)
    {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(db, c, db.grabImage(c), ViewSize.TINY, false);
        setX(p.getX());
        setY(p.getY());
        Scene scene = new Scene(node);
        setScene(scene);
    }

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