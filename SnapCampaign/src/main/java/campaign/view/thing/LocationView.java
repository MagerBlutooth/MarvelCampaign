package campaign.view.thing;

import campaign.model.thing.Location;
import campaign.model.thing.ThingType;
import campaign.view.grabber.ThingImageGrabber;
import campaign.view.IconImage;
import campaign.view.ViewSize;

public class LocationView extends ThingView {

    public LocationView()
    {
        fxmlCampaignGrabber.grabFXML("locationView.fxml", this);
    }

    public void initialize(Location w, ViewSize size, boolean e)
    {
        editable = e;
        if(editable)
            addDragAndDrop();
        ThingImageGrabber imageGrabber = new ThingImageGrabber(ThingType.LOCATION);
        if(w != null) {
            image = imageGrabber.grabImage(w.getID());
            setImage(image, size);
        }
        getController().initialize(w, size);
    }

    public IconImage getImage() {
        return image;
    }

}
