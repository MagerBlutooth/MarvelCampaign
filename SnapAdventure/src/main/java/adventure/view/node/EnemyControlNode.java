package adventure.view.node;

import adventure.model.thing.Enemy;
import adventure.view.AdvTooltip;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class EnemyControlNode extends ControlNode<Enemy> {

    @Override
        public void initialize(MainDatabase db, Enemy e, IconImage i, ViewSize v, boolean revealed) {
            mainDatabase = db;
            targetType = TargetType.CARD;
            subject = e;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
        }

    public void createTooltip(String effect) {
        AdvTooltip myToolTip = new AdvTooltip();
        myToolTip.setText(effect);
        myToolTip.initialize(this);
        Tooltip.install(this, myToolTip);
    }

    public void refresh(Enemy e) {
        IconImage i = mainDatabase.grabImage(e.getSubject());
        subject = e;
        imageView.setImage(i);
    }

}
