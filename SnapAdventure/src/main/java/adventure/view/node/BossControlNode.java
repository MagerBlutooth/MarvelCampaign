package adventure.view.node;

import adventure.model.thing.Boss;
import javafx.scene.control.Tooltip;
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
            createTooltip(b.getEffect());
        }

    private void createTooltip(String effect) {
        Tooltip tooltip = new Tooltip(effect);
        Tooltip.install(this, tooltip);
    }

    public void unreveal() {
        lowlight();
    }

    public void reveal() {
        highlight();
    }

    public void refresh(Boss boss) {
        IconImage i = mainDatabase.grabImage(boss.getCard());
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
