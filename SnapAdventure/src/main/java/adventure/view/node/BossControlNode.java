package adventure.view.node;

import adventure.model.thing.AdvCard;
import snapMain.controller.MainDatabase;
import snapMain.model.thing.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class BossControlNode extends ControlNode<AdvCard> {

    boolean revealed;

    @Override
        public void initialize(MainDatabase db, AdvCard b, IconImage i, ViewSize v, boolean revealed) {
            mainDatabase = db;
            targetType = TargetType.CARD;
            subject = b;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            setEnabled(b.isEnabled());
            if(!revealed)
                unreveal();
        }

    public void unreveal() {
        imageView.setImage(mainDatabase.grabBlankImage(TargetType.LOCATION));
        revealed = false;
    }

    public void reveal() {
        imageView.setImage(mainDatabase.grabImage(subject, TargetType.LOCATION));
        revealed = true;
    }
}
