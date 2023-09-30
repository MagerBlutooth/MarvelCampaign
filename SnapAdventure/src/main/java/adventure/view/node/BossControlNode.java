package adventure.view.node;

import adventure.model.Boss;
import campaign.controller.MainDatabase;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;

public class BossControlNode extends ControlNode<Boss> {

    boolean revealed;

    @Override
        public void initialize(MainDatabase db, Boss b, IconImage i, ViewSize v, boolean revealed) {
            mainDatabase = db;
            thingType = ThingType.CARD;
            subject = b;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            setEnabled(b.isEnabled());
            if(!revealed)
                unreveal();
        }

    public void unreveal() {
        imageView.setImage(mainDatabase.grabBlankImage(ThingType.LOCATION));
        revealed = false;
    }

    public void reveal() {
        imageView.setImage(mainDatabase.grabImage(subject, ThingType.LOCATION));
        revealed = true;
    }
}
