package adventure.view.node;

import adventure.model.Boss;
import campaign.controller.ControllerDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.grabber.ImageGrabber;
import campaign.view.node.control.ControlNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BossControlNode extends ControlNode<Boss> {

    boolean revealed;

    @Override
        public void initialize(ControllerDatabase db, Boss b, IconImage i, ViewSize v, boolean revealed) {
            controllerDatabase = db;
            thingType = ThingType.CARD;
            subject = b;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            setEnabled(b.isEnabled());
            if(!revealed)
                unreveal();
        }

    public void unreveal() {
        imageView.setImage(controllerDatabase.grabBlankImage(ThingType.LOCATION));
        revealed = false;
    }

    public void reveal() {
        imageView.setImage(controllerDatabase.grabImage(subject, ThingType.LOCATION));
        revealed = true;
    }
}
