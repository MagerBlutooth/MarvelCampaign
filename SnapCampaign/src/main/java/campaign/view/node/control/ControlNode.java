package campaign.view.node.control;

import campaign.controller.ControllerDatabase;
import campaign.view.grabber.ImageGrabber;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.Location;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;

public class ControlNode<T extends Thing> extends StackPane {

    ControllerDatabase controllerDatabase;
    private T subject;

    private boolean enabled;

    ImageView imageView;

    ThingType thingType;
    AnchorPane starPane;

    public Image getImage() {
        return imageView.getImage();
    }

    public ControlNode()
    {
        starPane = new AnchorPane();
        imageView = new ImageView();
        getChildren().add(imageView);
    }

    public void initialize(ControllerDatabase db, T t, IconImage i, ViewSize v, boolean blind) {

        controllerDatabase = db;
        thingType = t.getThingType();
        ThingDatabase<T> thingDatabase = controllerDatabase.lookupDatabase(thingType);
        subject = thingDatabase.lookup(t.getID());
        if(subject == null)
            subject = t;
        imageView.setImage(i);
        imageView.setFitWidth(v.getSizeVal());
        imageView.setFitHeight(v.getSizeVal());
        setEnabled(t.isEnabled());

        if(t instanceof Card)
        {
            Card c = (Card)subject;
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

    private void createCaptainView(ViewSize v) {
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

    private Image grabStarIcon() {
        ImageGrabber imageGrabber = new ImageGrabber();
        return imageGrabber.grabStarImage();
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

    public ThingType getThingType()
    {
        return thingType;
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
}
