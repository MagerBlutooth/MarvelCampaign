package adventure.view.node;

import adventure.model.thing.Boss;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class BossControlNode extends ControlNode<Boss> {

    @Override
        public void initialize(MainDatabase db, Boss b, IconImage i, ViewSize v, boolean revealed) {
            mainDatabase = db;
            targetType = TargetType.CARD;
            subject = b;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            if(!revealed)
                unreveal();
        }

    public void unreveal() {
        imageView.setImage(mainDatabase.grabBlankImage(TargetType.LOCATION));
    }

    public void reveal() {
        imageView.setImage(mainDatabase.grabImage(subject.getCard(), TargetType.CARD));
    }

    public void refresh(Boss boss) {
        IconImage i = mainDatabase.grabImage(boss.getCard(), TargetType.CARD);
        subject = boss;
        imageView.setImage(i);
        if(!boss.isRevealed())
            unreveal();
        else
            reveal();
    }

    public boolean isRevealed() {
        return subject.isRevealed();
    }
}
