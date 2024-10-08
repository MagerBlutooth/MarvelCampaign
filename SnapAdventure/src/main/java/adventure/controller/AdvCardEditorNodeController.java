package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.target.base.AdvCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import snapMain.controller.editor.BasicNodeController;
import snapMain.model.target.Card;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.thing.CardView;

public class AdvCardEditorNodeController extends BasicNodeController<AdvMainDatabase, Card> {

    @FXML
    CardView imageView;
    @FXML
    TextArea effectField;
    AdvMainDatabase database;
    Card card;

    public void initialize(AdvMainDatabase d, AdvCard c) {
        database = d;
        card = c.getCard();
        effectField.setText(c.getEffect());
        setImages();
    }

    private void setImages() {
        IconImage i = database.grabImage(card);
        imageView.setImage(i, ViewSize.GIANT);
    }

    public AdvCard generateAdvCard() {
        AdvCard c = new AdvCard(card);
        c.setEffect(effectField.getText());
        return c;
    }
}
