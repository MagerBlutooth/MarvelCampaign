package snapMain.view.node.control;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
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
    protected Tooltip tooltip;

    protected TargetType targetType;

    public Image getImage() {
        return imageView.getImage();
    }

    public ControlNode()
    {
        imageView = new ImageView();
        getChildren().add(imageView);
    }

    public void initialize(IconImage i, ViewSize v)
    {
        imageView.setImage(i);
        imageView.setFitWidth(v.getSizeVal());
        imageView.setFitHeight(v.getSizeVal());
        highlighted = true;
    }

    public void initialize(MainDatabase db, T t, IconImage i, ViewSize v, boolean blind) {

        this.initialize(i, v);
        mainDatabase = db;
        targetType = t.getTargetType();
        subject = t;
        setEnabled(t.isEnabled());
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

    public void setDull(boolean dull) {
        imageView.setPreserveRatio(true);
        if(dull) {
            ColorAdjust grayscale = new ColorAdjust();
            grayscale.setSaturation(-1.0);
            imageView.setEffect(grayscale);
            imageView.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.7),
                            new KeyValue(grayscale.saturationProperty(), 0)));
            timeline.setAutoReverse(true);
            timeline.setCycleCount(Animation.INDEFINITE);
            imageView.setOnMouseEntered(e -> timeline.play());
            imageView.setOnMouseExited(e -> {
                timeline.jumpTo(Duration.ZERO);
                timeline.stop();
            });
        }
        else {
            DropShadow borderGlow = new DropShadow();
            int depth = 40;
            borderGlow.setWidth(depth);
            borderGlow.setHeight(depth);
            Glow glow = new Glow(0);
            borderGlow.setInput(glow);
            borderGlow.setColor(Color.GOLD);
            imageView.setEffect(borderGlow);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.7),
                            new KeyValue(glow.levelProperty(), 0.5)));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.setAutoReverse(true);
            imageView.setOnMouseEntered(e -> timeline.play());
            imageView.setOnMouseExited(e -> {
                timeline.jumpTo(Duration.ZERO);
                timeline.stop();
            });

            imageView.setOpacity(1.0);
        }
    }

    public void setInvalid(boolean invalid)
    {
        Blend red = new Blend();
        if(invalid) {
            red.setMode(BlendMode.MULTIPLY);
            ColorAdjust goldPlate = new ColorAdjust();
            goldPlate.setSaturation(-1.0);
            Lighting lighting = new Lighting(new Light.Distant(0, 70, Color.CRIMSON));
            red.setTopInput(goldPlate);
            red.setBottomInput(lighting);
            red.setOpacity(0.1);
            imageView.setEffect(red);
        }
        else
            imageView.setEffect(null);
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
