package snapMain.view.thing;

import snapMain.controller.MainDatabase;
import snapMain.controller.view.CardViewController;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;

public class CardView extends ThingView<Card> {

    CardViewController controller;

    public CardView()
    {
        fxmlCampaignGrabber.grabFXML("cardView.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase cd, Card c, ViewSize size, boolean editable)
    {
        viewSize = size;
        this.editable = editable;
        if(editable)
            addDragAndDrop();
        controller.initialize(c, size);
        image = cd.grabImage(c, TargetType.CARD);
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
