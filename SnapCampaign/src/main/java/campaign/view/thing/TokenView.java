package campaign.view.thing;

import campaign.controller.view.TokenViewController;
import campaign.model.thing.ThingType;
import campaign.model.thing.Token;
import campaign.view.grabber.ThingImageGrabber;
import campaign.view.IconImage;
import campaign.view.ViewSize;

public class TokenView extends ThingView {

    TokenViewController controller;

    public TokenView()
    {
        fxmlCampaignGrabber.grabFXML("tokenView.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(Token t, ViewSize size, boolean e)
    {
        viewSize = size;
        editable = e;
        if(editable)
            addDragAndDrop();
        controller.initialize(t, size);
        ThingImageGrabber imageGrabber = new ThingImageGrabber(ThingType.TOKEN);

        image = imageGrabber.grabImage(t.getID());
        setImage(image, size);

    }

    public IconImage getImage() {
        return image;
    }

    public Token getToken() {
        return controller.getToken();
    }
}
