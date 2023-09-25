package campaign.view.thing;

import campaign.controller.ControllerDatabase;
import campaign.controller.view.CardViewController;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;

public class CardView extends ThingView<Card> {

    CardViewController controller;

    public CardView()
    {
        fxmlCampaignGrabber.grabFXML("cardView.fxml", this);
        controller = fxmlCampaignGrabber.getController();
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
