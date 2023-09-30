package adventure.controller;

import adventure.model.AdvMainDatabase;
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

public class BossEditorNodeController extends BasicNodeController<AdvMainDatabase, Card> {

    @FXML
    CardView imageView;
    @FXML
    Label nameLabel;
    @FXML
    TextArea effectField;
    AdvMainDatabase database;
    Card card;

    public void initialize(AdvMainDatabase d, Boss b) {
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
