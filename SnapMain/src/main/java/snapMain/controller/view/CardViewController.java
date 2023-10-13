package snapMain.controller.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.grabber.ThingImageGrabber;
import snapMain.view.thing.CardView;


public class CardViewController extends CampaignViewController<Card> {

    @FXML
    CardView cardView;
    @FXML
    ImageView mainImage;

    ThingImageGrabber imageGrabber = new ThingImageGrabber(TargetType.CARD);
    Card card;
    ViewSize viewSize;

    @Override
    public void initialize(Card c, ViewSize v) {
        viewSize = v;
        setCard(c);
        cardView.setViewSize(v);
    }

    @Override
    public void setMainImage(IconImage img, ViewSize v) {
        mainImage.setImage(img);
        mainImage.setPreserveRatio(false);
        mainImage.setFitWidth(v.getSizeVal());
        mainImage.setFitHeight(v.getSizeVal());
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card c) {

        card = c;
        setMainImage(imageGrabber.grabImage(card.getID()), viewSize);
    }
}
