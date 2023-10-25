package adventure.view.node;

import adventure.model.target.Enemy;
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
import snapMain.view.grabber.IconConstant;
import snapMain.view.node.control.ControlNode;

public class EnemyControlNode extends ControlNode<Enemy> {

    protected AnchorPane secondaryPane;
    ImageView secondaryView;
    boolean secondaryDefined;
    boolean isRevealed;
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
            isRevealed = revealed;
        }

    public void refresh(Enemy e, boolean reveal) {
        subject = e;
        setRevealed(reveal);
        setSecondaryImage();
    }

    public void unreveal()
    {
        isRevealed = false;
        this.setEffect(null);
        IconImage image = mainDatabase.grabIcon(IconConstant.BOSS);
        imageView.setImage(image);
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.WHITE);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        imageView.setEffect(borderGlow);
    }

    public void reveal() {
        isRevealed = true;
        IconImage i = mainDatabase.grabImage(subject.getSubject());
        imageView.setImage(i);
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

    public Playable getSecondarySubject() {
        return subject.getSecondarySubject();
    }
}
