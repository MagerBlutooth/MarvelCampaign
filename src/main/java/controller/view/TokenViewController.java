package controller.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import model.thing.ThingType;
import model.thing.Token;
import view.IconImage;
import view.ViewSize;
import view.grabber.ThingImageGrabber;
import view.thing.TokenView;


public class TokenViewController extends CampaignViewController<Token> {

    @FXML
    TokenView tokenView;
    @FXML
    ImageView mainImage;

     Token token;

    @Override
    public void initialize(Token t, ViewSize v) {
        ThingImageGrabber imageGrabber = new ThingImageGrabber(ThingType.CARD);
        token = t;
        tokenView.setViewSize(v);
        setTooltip();
        setMainImage(imageGrabber.grabImage(token.getID()), v);
}

    private void setTooltip() {
        Tooltip cardToolTip = new Tooltip(token.getName());
        cardToolTip.setFont(new Font("Ubuntu", 20));
        tokenView.setOnMouseEntered(e -> {
            Node node = (Node) e.getSource();
            cardToolTip.show(node, e.getScreenX() + 50, e.getScreenY());
        });
        tokenView.setOnMouseExited(e -> cardToolTip.hide());
    }

    @Override
    public void setMainImage(IconImage img, ViewSize v) {
        mainImage.setImage(img);
        mainImage.setPreserveRatio(false);
        mainImage.setFitWidth(v.getSizeVal());
        mainImage.setFitHeight(v.getSizeVal());
    }

    public Token getToken() {
        return token;
    }
}
