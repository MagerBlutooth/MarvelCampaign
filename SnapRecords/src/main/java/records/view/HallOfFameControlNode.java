package records.view;

import campaign.controller.MainDatabase;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import campaign.model.thing.ThingType;
import records.model.HallOfFameEntry;

public class HallOfFameControlNode extends ControlNode<HallOfFameEntry> {

    MainDatabase mainDatabase;
    private HallOfFameEntry subject;
    private boolean enabled;

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
    public void initialize(MainDatabase db, HallOfFameEntry entry, IconImage i, ViewSize v, boolean blind) {

        mainDatabase = db;
        thingType = ThingType.HALL_OF_FAME;
        subject = entry;
        imageView.setImage(i);
        imageView.setFitWidth(v.getSizeVal());
        imageView.setFitHeight(v.getSizeVal());
        setEnabled(true);
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

    @Override
    public void setEnabled(boolean e)
    {
        enabled = e;
        subject.setEnabled(enabled);
        if(enabled)
            highlight();
        else
            lowlight();
    }
}
