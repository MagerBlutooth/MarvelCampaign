package view.thing;

import controller.view.CardViewController;
import controller.view.TokenViewController;
import model.thing.Card;
import model.thing.ThingType;
import model.thing.Token;
import view.IconImage;
import view.ViewSize;
import view.grabber.ThingImageGrabber;

public class TokenView extends ThingView {

    TokenViewController controller;

    public TokenView()
    {
        fxmlGrabber.grabFXML("tokenView.fxml", this);
        controller = fxmlGrabber.getController();
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
