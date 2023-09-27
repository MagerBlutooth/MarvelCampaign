package adventure.controller;

import adventure.model.AdvControllerDatabase;
import adventure.model.Boss;
import campaign.controller.editor.BasicNodeController;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.thing.CardView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class BossEditorNodeController extends BasicNodeController<AdvControllerDatabase, Card> {

    @FXML
    CardView imageView;
    @FXML
    Label nameLabel;
    @FXML
    TextArea effectField;
    AdvControllerDatabase database;
    Card card;

    public void initialize(AdvControllerDatabase d, Boss b) {
        database = d;
        card = b.getCard();
        nameLabel.setText(card.getName());
        effectField.setText(b.getEffect());
        setImages();
    }

    private void setImages() {
        IconImage i = database.grabImage(card, ThingType.CARD);
        imageView.setImage(i, ViewSize.LARGE);
    }

    public Boss generateBoss() {
        Boss b = new Boss(card);
        b.setEffect(effectField.getText());
        return b;
    }
}
