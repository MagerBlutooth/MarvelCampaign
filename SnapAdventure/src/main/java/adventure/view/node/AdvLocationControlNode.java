package adventure.view.node;

import adventure.model.thing.AdvLocation;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class AdvLocationControlNode extends ControlNode<AdvLocation> {

    boolean revealed;

    @Override
        public void initialize(MainDatabase db, AdvLocation s, IconImage i, ViewSize v, boolean revealed) {
            mainDatabase = db;
            targetType = TargetType.LOCATION;
            subject = s;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            setEnabled(s.isEnabled());
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
        imageView.setImage(mainDatabase.grabBlankImage(TargetType.LOCATION));
        revealed = false;
    }

    public void reveal() {
        imageView.setImage(mainDatabase.grabImage(subject));
        revealed = true;
    }

    public void update(AdvLocation l) {
        subject = l;
        IconImage i = mainDatabase.grabImage(l);
        imageView.setImage(i);
    }
}
