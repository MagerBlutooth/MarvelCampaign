package snapMain.view.thing;

import snapMain.model.target.Location;
import snapMain.model.target.TargetType;
import snapMain.view.grabber.ThingImageGrabber;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;

public class LocationView extends ThingView {

    public LocationView()
    {
        fxmlMainGrabber.grabFXML("locationView.fxml", this);
    }

    public void initialize(Location w, ViewSize size, boolean e)
    {
        editable = e;
        if(editable)
            addDragAndDrop();
        ThingImageGrabber imageGrabber = new ThingImageGrabber(TargetType.LOCATION);
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
