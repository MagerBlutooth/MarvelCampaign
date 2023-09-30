package adventure.view.node;

import adventure.model.Section;
import campaign.controller.MainDatabase;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;

public class SectionControlNode extends ControlNode<Section> {

    boolean revealed;

    @Override
        public void initialize(MainDatabase db, Section s, IconImage i, ViewSize v, boolean revealed) {
            mainDatabase = db;
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
        imageView.setImage(mainDatabase.grabBlankImage(ThingType.LOCATION));
        revealed = false;
    }

    public void reveal() {
        imageView.setImage(mainDatabase.grabImage(subject, ThingType.LOCATION));
        revealed = true;
    }
}
