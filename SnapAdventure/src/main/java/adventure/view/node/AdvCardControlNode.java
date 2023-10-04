package adventure.view.node;

import adventure.model.thing.AdvCard;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class AdvCardControlNode extends ControlNode<AdvCard> {

    boolean revealed;

    @Override
        public void initialize(MainDatabase db, AdvCard c, IconImage i, ViewSize v, boolean revealed) {
            mainDatabase = db;
            targetType = TargetType.LOCATION;
            subject = c;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            setEnabled(c.isEnabled());
            if(!revealed)
                unreveal();
        }
        public void toggleReveal()
        {
            if(revealed)
                unreveal();
            else
                reveal();
        }

    public void unreveal() {
        imageView.setImage(mainDatabase.grabBlankImage(TargetType.ADV_CARD));
        revealed = false;
    }

    public void reveal() {
        imageView.setImage(mainDatabase.grabImage(subject));
        revealed = true;
    }

    public void update(AdvCard c) {
        subject = c;
        IconImage i = mainDatabase.grabImage(c);
        imageView.setImage(i);
    }
}
