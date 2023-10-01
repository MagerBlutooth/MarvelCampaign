package records.view;

import snapMain.controller.MainDatabase;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import snapMain.model.thing.TargetType;
import records.model.HallOfFameEntry;

public class HallOfFameControlNode extends ControlNode<HallOfFameEntry> {

    MainDatabase mainDatabase;
    private HallOfFameEntry subject;
    private boolean enabled;

    ImageView imageView;

    TargetType targetType;
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
        targetType = TargetType.HALL_OF_FAME;
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
