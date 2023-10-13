package adventure.view.node;

import adventure.model.thing.Enemy;
import adventure.view.AdvTooltip;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
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
        Tooltip.install(this, myToolTip);
    }

    public void refresh(Enemy e) {
        IconImage i = mainDatabase.grabImage(e.getSubject());
        subject = e;
        imageView.setImage(i);
    }

    public void unreveal()
    {
        this.setEffect(null);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1);
        this.setEffect(colorAdjust);
    }

    public void reveal() {
        this.setEffect(null);
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.RED);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        this.setEffect(borderGlow);
    }

    public void setRevealed(boolean r) {
        if(r)
            reveal();
        else
            unreveal();
    }
}
