package adventure.view.popup;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

import java.util.Objects;

public class CardDisplayPopup extends AdvPopup {

    GridDisplayNode<Card> cardGridDisplayNode;
    CardList cards;

    public CardDisplayPopup(MainDatabase db, Card c, Point2D p)
    {
        ControlNode<Card> node = new ControlNode<>();
        node.initialize(db, c, db.grabImage(c), ViewSize.TINY, false);
        setX(p.getX());
        setY(p.getY());
        Scene scene = new Scene(node);
        setScene(scene);
        node.setBorder((new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        getScene().getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css_adventure/mainStyle.css")).toExternalForm());

    }

    //TODO: Change ActionController for CardPopupDisplays for stationed/captured/eliminated cards etc. to have different ContextMenus
    public CardDisplayPopup(CardList c, Point2D p)
    {
        cards = c;
        cardGridDisplayNode = new GridDisplayNode<>();
        cardGridDisplayNode.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        cardGridDisplayNode.setBorder((new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        setX(p.getX());
        setY(p.getY());
        Scene scene = new Scene(cardGridDisplayNode);
        setScene(scene);
        getScene().getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css_adventure/mainStyle.css")).toExternalForm());
    }

    public void initialize(GridActionController<Card> controller)
    {
        cardGridDisplayNode.initialize(cards, TargetType.CARD, controller, ViewSize.TINY, false);
    }

    public GridDisplayController<Card> getGridDisplayController() {
        return cardGridDisplayNode.getController();
    }
}
