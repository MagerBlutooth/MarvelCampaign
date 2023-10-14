package adventure.view.node;

import adventure.model.thing.AdvCard;
import adventure.model.thing.Enemy;
import adventure.view.AdvTooltip;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import snapMain.controller.MainDatabase;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Playable;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class EnemyControlNode extends ControlNode<Enemy> {

    protected AnchorPane secondaryPane;
    ImageView secondaryView;
    boolean secondaryDefined;
    @Override
        public void initialize(MainDatabase db, Enemy e, IconImage i, ViewSize v, boolean revealed) {
            mainDatabase = db;
            targetType = TargetType.CARD;
            subject = e;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            secondaryPane = new AnchorPane();
            createSecondaryView(v);
            secondaryDefined = subject.getSecondarySubject().getID() == SnapMainConstants.MOOK_ICON_ID;
        }

    public void refresh(Enemy e) {
        IconImage i = mainDatabase.grabImage(e.getSubject());
        subject = e;
        imageView.setImage(i);
        setSecondaryImage();
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

    public void setSecondary(Playable p)
    {
        subject.setSecondarySubject(p);
        setSecondaryImage();
        secondaryPane.setVisible(true);
        secondaryDefined = true;
    }

    public void swapPrimaryAndSecondary()
    {
        subject.swapPrimaryAndSecondary();
        refresh(subject);
    }

    private void createSecondaryView(ViewSize v) {
        secondaryPane.setMinSize(v.getSizeVal(), v.getSizeVal());
        secondaryPane.setMaxSize(v.getSizeVal(),v.getSizeVal());
        setSecondaryImage();
        getChildren().add(secondaryPane);
        secondaryPane.setVisible(false);
    }

    private void setSecondaryImage()
    {
        IconImage secondaryImage = mainDatabase.grabImage(subject.getSecondarySubject());
        secondaryView = new ImageView(secondaryImage);
        secondaryView.setImage(mainDatabase.grabImage(subject.getSecondarySubject()));
        secondaryPane.getChildren().clear();
        secondaryView.setFitWidth(40);
        secondaryView.setFitHeight(40);
        AnchorPane.setRightAnchor(secondaryView, 20.0);
        AnchorPane.setBottomAnchor(secondaryView, 0.0);
        secondaryPane.toFront();
        secondaryPane.getChildren().add(secondaryView);
    }

    public boolean noSecondaryDefined() {
        return secondaryDefined;
    }
}
