package view.thing;

import controller.ControllerDatabase;
import controller.view.CardViewController;
import model.thing.Card;
import model.thing.ThingType;
import view.IconImage;
import view.ViewSize;
import view.grabber.ThingImageGrabber;

public class CardView extends ThingView<Card> {

    CardViewController controller;

    public CardView()
    {
        fxmlGrabber.grabFXML("cardView.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase cd, Card c, ViewSize size, boolean editable)
    {
        viewSize = size;
        this.editable = editable;
        if(editable)
            addDragAndDrop();
        controller.initialize(c, size);
        image = cd.grabImage(c, ThingType.CARD);
        setImage(image, size);
    }

    public void refresh(Card c)
    {
        controller.setCard(c);
    }

    public IconImage getImage() {
        return image;
    }

    public Card getCard() {
        return controller.getCard();
    }

    public void disableTooltip()
    {
        controller.disableTooltip();
    }
}
