package snapMain.view.node.control;

import snapMain.controller.MainDatabase;
import snapMain.model.target.BaseObject;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import snapMain.model.target.TargetType;

public class EmptyControlNode<T extends BaseObject> extends ControlNode<T> {

    MainDatabase mainDatabase;
    private T subject;

    private boolean enabled;

    ImageView imageView;

    TargetType targetType;

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
