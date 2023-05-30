package view.node.control;

import controller.ControllerDatabase;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.database.ThingDatabase;
import model.thing.Thing;
import model.thing.ThingType;
import view.IconImage;
import view.ViewSize;

public class EmptyControlNode<T extends Thing> extends ControlNode<T> {

    ControllerDatabase controllerDatabase;
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

    public void initialize(ControllerDatabase db, IconImage i, ViewSize v) {

        controllerDatabase = db;
        imageView.setImage(i);
        imageView.setFitWidth(v.getSizeVal());
        imageView.setFitHeight(v.getSizeVal());
    }
}
