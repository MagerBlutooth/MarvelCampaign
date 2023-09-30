package campaign.view.node.control;

import campaign.controller.MainDatabase;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;

public class EmptyControlNode<T extends Thing> extends ControlNode<T> {

    MainDatabase mainDatabase;
    private T subject;

    private boolean enabled;

    ImageView imageView;

    ThingType thingType;

    public Image getImage() {
        return imageView.getImage();
    }

    public EmptyControlNode()
    {
        imageView = new ImageView();
        getChildren().add(imageView);
    }

    public void initialize(MainDatabase db, IconImage i, ViewSize v) {

        mainDatabase = db;
        imageView.setImage(i);
        imageView.setFitWidth(v.getSizeVal());
        imageView.setFitHeight(v.getSizeVal());
    }
}
