package adventure.view.node;

import adventure.model.target.AdvCard;
import adventure.model.target.AdvToken;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class AdvTokenControlNode extends ControlNode<AdvToken> {

    boolean revealed;

    @Override
        public void initialize(MainDatabase db, AdvToken t, IconImage i, ViewSize v, boolean revealed) {
            mainDatabase = db;
            targetType = TargetType.ADV_TOKEN;
            subject = t;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            setEnabled(t.isEnabled());
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

    public void update(AdvToken t) {
        subject = t;
        IconImage i = mainDatabase.grabImage(t);
        imageView.setImage(i);
    }
}
