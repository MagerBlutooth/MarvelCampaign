package adventure.view.node;

import campaign.controller.ControllerDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.grabber.ImageGrabber;
import campaign.view.node.control.ControlNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BossControlNode extends ControlNode<Card> {


    @Override
        public void initialize(ControllerDatabase db, Card c, IconImage i, ViewSize v, boolean blind) {

            controllerDatabase = db;
            thingType = ThingType.CARD;
            subject = c;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            setEnabled(c.isEnabled());
            if(!blind && c.isCaptain())
                createCaptainView(v);
            setDamage(c.isWounded());
            setCaptain(c.isCaptain());
        }

        private void createCaptainView(ViewSize v) {
            starPane.setMinSize(v.getSizeVal(), v.getSizeVal());
            starPane.setMaxSize(v.getSizeVal(),v.getSizeVal());
            ImageView captainStar = new ImageView(grabStarIcon());
            captainStar.setFitWidth(30);
            captainStar.setFitHeight(30);
            AnchorPane.setLeftAnchor(captainStar, 0.0);
            AnchorPane.setBottomAnchor(captainStar, 0.0);
            starPane.getChildren().add(captainStar);
            this.getChildren().add(starPane);
            starPane.toFront();
            starPane.setVisible(false);
        }

        private Image grabStarIcon() {
            ImageGrabber imageGrabber = new ImageGrabber();
            return imageGrabber.grabStarImage();
        }

}
