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
