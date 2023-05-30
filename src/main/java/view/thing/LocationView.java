package view.thing;

import model.thing.Location;
import model.thing.ThingType;
import view.IconImage;
import view.ViewSize;
import view.grabber.ThingImageGrabber;

public class LocationView extends ThingView {

    public LocationView()
    {
        fxmlGrabber.grabFXML("locationView.fxml", this);
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
