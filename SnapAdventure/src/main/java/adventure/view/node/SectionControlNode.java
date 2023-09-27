package adventure.view.node;

import campaign.controller.ControllerDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.Location;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.grabber.ImageGrabber;
import campaign.view.node.control.ControlNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SectionControlNode extends ControlNode<Location> {
    @Override
        public void initialize(ControllerDatabase db, Location l, IconImage i, ViewSize v, boolean blind) {
            controllerDatabase = db;
            thingType = ThingType.LOCATION;
            subject = l;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            setEnabled(l.isEnabled());
        }

}
