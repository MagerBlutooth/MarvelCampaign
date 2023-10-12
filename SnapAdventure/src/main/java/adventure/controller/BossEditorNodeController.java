package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import snapMain.controller.editor.BasicNodeController;
import snapMain.model.target.Card;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.thing.CardView;

public class BossEditorNodeController extends BasicNodeController<AdvMainDatabase, Card> {

    @FXML
    CardView imageView;
    @FXML
    Label nameLabel;
    @FXML
    TextArea effectField;
    AdvMainDatabase database;
    Card card;

    public void initialize(AdvMainDatabase d, AdvCard b) {
        database = d;
        card = b.getCard();
        nameLabel.setText(card.getName());
        effectField.setText(b.getEffect());
        setImages();
    }

    private void setImages() {
        IconImage i = database.grabImage(card);
        imageView.setImage(i, ViewSize.LARGE);
    }

    public AdvCard generateBoss() {
        AdvCard b = new AdvCard(card);
        b.setEffect(effectField.getText());
        return b;
    }
}
