package adventure.view.node;

import snapMain.controller.MainDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;

import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class DeckItemControlNode extends ControlNode<Card> {

    public void initialize(MainDatabase db, Card c, CardList deck, ViewSize v) {

        mainDatabase = db;
        TargetDatabase<Card> targetDatabase = mainDatabase.lookupDatabase(TargetType.CARD);
        subject = targetDatabase.lookup(c.getID());
        if(subject == null)
            subject = c;
        imageView.setImage(mainDatabase.grabImage(c));
        imageView.setFitWidth(v.getSizeVal());
        imageView.setFitHeight(v.getSizeVal());
        createCaptainView(v);
        setHighlighted(true);
        setDamage(c.isWounded());
        setCaptain(c.isCaptain());
    }
}