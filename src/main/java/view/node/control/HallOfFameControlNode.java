package view.node.control;

import controller.ControllerDatabase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.thing.HallOfFameEntry;
import model.thing.ThingType;
import view.IconImage;
import view.ViewSize;

public class HallOfFameControlNode extends ControlNode<HallOfFameEntry> {

    ControllerDatabase controllerDatabase;
    private HallOfFameEntry subject;

    ImageView imageView;

    ThingType thingType;
    AnchorPane starPane;

    public Image getImage() {
        return imageView.getImage();
    }

    public HallOfFameControlNode()
    {
        starPane = new AnchorPane();
        imageView = new ImageView();
        getChildren().add(imageView);
    }

     @Override
    public void initialize(ControllerDatabase db, HallOfFameEntry entry, IconImage i, ViewSize v, boolean blind) {

        controllerDatabase = db;
        thingType = ThingType.HALL_OF_FAME;
        subject = entry;
        imageView.setImage(i);
        imageView.setFitWidth(v.getSizeVal());
        imageView.setFitHeight(v.getSizeVal());
    }

    public void highlight()
    {
        imageView.setOpacity(1.0);
    }

    public void lowlight()
    {
        imageView.setOpacity(0.5);
    }

    @Override
    public HallOfFameEntry getSubject()
    {
        return subject;
    }

}
