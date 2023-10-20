package adventure.view.node;

import adventure.model.target.ActiveCard;
import snapMain.controller.MainDatabase;

import snapMain.model.target.StatusEffect;
import snapMain.view.ViewSize;

public class DeckItemControlNode extends ActiveCardControlNode {

    public void initialize(MainDatabase db, ActiveCard c, ViewSize v) {

        mainDatabase = db;
        subject = c;
        imageView.setImage(mainDatabase.grabImage(c));
        imageView.setFitWidth(v.getSizeVal());
        imageView.setFitHeight(v.getSizeVal());
        createCaptainView(v);
        setDamage(c.hasStatus(StatusEffect.WOUND));
        setCaptain(c.hasStatus(StatusEffect.CAPTAIN));
        setHighlighted(true);
    }
}
