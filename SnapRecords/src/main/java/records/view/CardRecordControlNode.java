package records.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import records.view.fxml.FXMLRecordGrabber;
import snapMain.controller.MainDatabase;
import snapMain.model.target.Card;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class CardRecordControlNode extends ControlNode<Card> {

    @FXML
    Label countLabel;
    @FXML
    ImageView image;

    public CardRecordControlNode()
    {
        FXMLRecordGrabber grabber = new FXMLRecordGrabber();
        grabber.grabFXML("cardRecordControlNode.fxml", this, this);
    }
    public void initialize(MainDatabase db, Card c, int count)
    {
        countLabel.setText(count+"");
        image.setImage(db.grabImage(c));
        image.setFitHeight(ViewSize.MEDIUM.getSizeVal());
        image.setFitWidth(ViewSize.MEDIUM.getSizeVal());
    }
}
