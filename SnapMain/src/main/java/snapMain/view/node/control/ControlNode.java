package snapMain.view.node.control;

import snapMain.controller.MainDatabase;
import snapMain.model.target.*;
import snapMain.view.grabber.IconConstant;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;

public class ControlNode<T extends SnapTarget> extends StackPane {

    protected MainDatabase mainDatabase;
    protected T subject;

    private boolean enabled;
    protected boolean highlighted;

    protected ImageView imageView;

    protected TargetType targetType;
    protected AnchorPane starPane;

    public Image getImage() {
        return imageView.getImage();
    }

    public ControlNode()
    {
        starPane = new AnchorPane();
        imageView = new ImageView();
        getChildren().add(imageView);
    }

    public void initialize(MainDatabase db, T t, IconImage i, ViewSize v, boolean blind) {

        mainDatabase = db;
        targetType = t.getTargetType();
        subject = t;
        imageView.setImage(i);
        imageView.setFitWidth(v.getSizeVal());
        imageView.setFitHeight(v.getSizeVal());
        setEnabled(t.isEnabled());
        highlighted = true;

        if(subject instanceof Card)
        {
            Card c = (Card)t;
            if(!blind && c.isCaptain())
                createCaptainView(v);
            setDamage(c.isWounded());
            setCaptain(c.isCaptain());
        }
        else if(t instanceof Location)
        {
            Location l = (Location)subject;
            setDamage(l.isRuined());
        }
    }

    protected void createCaptainView(ViewSize v) {
        starPane.setMinSize(v.getSizeVal(), v.getSizeVal());
        starPane.setMaxSize(v.getSizeVal(),v.getSizeVal());
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

    public T getSubject() {
        return subject;
    }


    public void setEnabled(boolean e)
    {
        enabled = e;
        subject.setEnabled(enabled);
        if(enabled)
            highlight();
        else
            lowlight();
    }

    public void highlight()
    {
        imageView.setOpacity(1.0);
    }

    public void lowlight()
    {
        imageView.setOpacity(0.5);
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public TargetType getThingType()
    {
        return targetType;
    }

    public void setDamage(boolean wounded)
    {
        if(wounded) {
            Blend blush = new Blend();
            blush.setMode(BlendMode.MULTIPLY);
            ColorAdjust redWound = new ColorAdjust();
            redWound.setSaturation(-1.0);
            Lighting lighting = new Lighting(new Light.Distant(0,70,Color.CRIMSON));
            blush.setTopInput(redWound);
            blush.setBottomInput(lighting);
            blush.setOpacity(0.1);
            imageView.setEffect(blush);
        }
        else
            imageView.setEffect(null);

    }

    public void setGolden(boolean golden) {
        Blend gold = new Blend();
        if(golden) {
            gold.setMode(BlendMode.MULTIPLY);
            ColorAdjust goldPlate = new ColorAdjust();
            goldPlate.setSaturation(-1.0);
            Lighting lighting = new Lighting(new Light.Distant(0, 70, Color.GOLD));
            gold.setTopInput(goldPlate);
            gold.setBottomInput(lighting);
            gold.setOpacity(0.1);
            imageView.setEffect(gold);
        }
        else
            imageView.setEffect(null);
    }

    public void setCaptain(boolean yes) {
        if(yes)
        {
            starPane.setVisible(true);
        }

        else {
            starPane.setVisible(false);
        }
    }

    public void toggleNodeLight() {
        setHighlighted(!highlighted);
    }

    public void setHighlighted(boolean h)
    {
        highlighted = h;
        if(h)
            highlight();
        else
            lowlight();
    }
}
