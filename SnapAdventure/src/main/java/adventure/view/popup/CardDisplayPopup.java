package adventure.view.popup;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.node.ActiveCardControlNode;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;

import java.util.Objects;

public class CardDisplayPopup extends AdvPopup {

    GridDisplayNode<ActiveCard> cardGridDisplayNode;
    ActiveCardList cards;

    public CardDisplayPopup(MainDatabase db, ActiveCard c, Point2D p)
    {
        ActiveCardControlNode node = new ActiveCardControlNode();
        node.initialize(db, c, db.grabImage(c), ViewSize.TINY, true);
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
    public CardDisplayPopup(ActiveCardList c, Point2D p)
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

    public void initialize(GridActionController<ActiveCard> controller)
    {
        cardGridDisplayNode.initialize(cards, TargetType.CARD, controller, ViewSize.TINY, true);
    }

    public GridDisplayController<ActiveCard> getGridDisplayController() {
        return cardGridDisplayNode.getController();
    }
}
