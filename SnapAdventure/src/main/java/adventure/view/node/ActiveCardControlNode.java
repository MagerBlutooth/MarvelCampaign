package adventure.view.node;

import adventure.model.target.ActiveCard;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import snapMain.controller.MainDatabase;
import snapMain.model.target.StatusEffect;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.grabber.IconConstant;
import snapMain.view.node.control.ControlNode;

public class ActiveCardControlNode extends ControlNode<ActiveCard> {

    AnchorPane starPane;
    AnchorPane sweatPane;

    public ActiveCardControlNode() {
        starPane = new AnchorPane();
    }

    @Override
    public void initialize(MainDatabase db, ActiveCard c, IconImage i, ViewSize v, boolean statusVisible) {
        mainDatabase = db;
        targetType = TargetType.LOCATION;
        subject = c;
        imageView.setImage(i);
        imageView.setFitWidth(v.getSizeVal());
        imageView.setFitHeight(v.getSizeVal());
        createCaptainView(v);
        if (statusVisible) {
            setDamage(c.hasStatus(StatusEffect.WOUND));
            setCaptain(c.hasStatus(StatusEffect.CAPTAIN));
            setExhausted(c.hasStatus(StatusEffect.EXHAUSTED));
        }
    }

    private void setExhausted(boolean exhausted) {
        sweatPane.setVisible(exhausted);
    }

    public void setCaptain(boolean yes) {
        starPane.setVisible(yes);
    }

    public void setDamage(boolean wounded) {
        if (wounded) {
            Blend blush = new Blend();
            blush.setMode(BlendMode.MULTIPLY);
            ColorAdjust redWound = new ColorAdjust();
            redWound.setSaturation(-1.0);
            Lighting lighting = new Lighting(new Light.Distant(0, 70, Color.CRIMSON));
            blush.setTopInput(redWound);
            blush.setBottomInput(lighting);
            blush.setOpacity(0.1);
            imageView.setEffect(blush);
        } else
            imageView.setEffect(null);

    }

    protected void createCaptainView(ViewSize v) {
        starPane.setMinSize(v.getSizeVal(), v.getSizeVal());
        starPane.setMaxSize(v.getSizeVal(), v.getSizeVal());
        ImageView captainStar = new ImageView(grabStarIcon());
        captainStar.setFitWidth(30);
        captainStar.setFitHeight(30);
        AnchorPane.setLeftAnchor(captainStar, 0.0);
        AnchorPane.setBottomAnchor(captainStar, 0.0);
        starPane.getChildren().add(captainStar);
        this.getChildren().add(starPane);
        starPane.toFront();
        starPane.setVisible(false);
    }

    private IconImage grabStarIcon() {
        return mainDatabase.grabIcon(IconConstant.STAR);
    }
}
