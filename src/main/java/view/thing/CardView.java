package view.thing;

import controller.view.CardViewController;
import model.thing.Card;
import model.thing.ThingType;
import view.IconImage;
import view.ViewSize;
import view.grabber.ThingImageGrabber;

public class CardView extends ThingView {

    CardViewController controller;

    public CardView()
    {
        fxmlGrabber.grabFXML("cardView.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(Card c, ViewSize size, boolean e)
    {
        viewSize = size;
        editable = e;
        if(editable)
            addDragAndDrop();
        controller.initialize(c, size);
        ThingImageGrabber imageGrabber = new ThingImageGrabber(ThingType.CARD);

        image = imageGrabber.grabImage(c.getID());
        setImage(image, size);
    }

    public IconImage getImage() {
        return image;
    }

    public Card getCard() {
        return controller.getCard();
    }
}
