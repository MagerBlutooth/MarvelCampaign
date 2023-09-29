package adventure.view.node;

import adventure.model.Section;
import campaign.controller.ControllerDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.Location;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.grabber.ImageGrabber;
import campaign.view.node.control.ControlNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SectionControlNode extends ControlNode<Section> {

    boolean revealed;

    @Override
        public void initialize(ControllerDatabase db, Section s, IconImage i, ViewSize v, boolean revealed) {
            controllerDatabase = db;
            thingType = ThingType.LOCATION;
            subject = s;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            setEnabled(s.isEnabled());
            if(!revealed)
                unreveal();
        }
        public void toggleReveal()
        {
            if(revealed)
                unreveal();
            else
                reveal();
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
