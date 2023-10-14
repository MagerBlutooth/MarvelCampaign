package adventure.view.popup;

import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvCard;
import adventure.view.node.AdvCardControlNode;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

import java.util.Objects;

public class CardDisplayPopup extends AdvPopup {

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
    public CardDisplayPopup(CardList cards, Point2D p, GridActionController<Card> controller)
    {
        GridDisplayNode<Card> cardDisplay = new GridDisplayNode<>();
        cardDisplay.initialize(cards, TargetType.CARD, controller, ViewSize.TINY, false);
        cardDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        cardDisplay.setBorder((new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        setX(p.getX());
        setY(p.getY());
        Scene scene = new Scene(cardDisplay);
        setScene(scene);
        getScene().getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css_adventure/mainStyle.css")).toExternalForm());
    }
}
