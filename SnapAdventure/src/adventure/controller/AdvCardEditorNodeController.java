package adventure.controller;

import adventure.model.Boss;
import campaign.controller.ControllerDatabase;
import campaign.controller.editor.BasicNodeController;
import campaign.model.thing.Card;
import campaign.model.thing.CardAttribute;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class AdvCardEditorNodeController extends BasicNodeController<Card> {

    ControllerDatabase database;
    Card card;
    @FXML
    Label nameLabel;
    @FXML
    HBox imageBox;
    @FXML
    TextArea effectField;

    public void initialize(ControllerDatabase d, Boss b) {
        database = d;
        card = b.getCard();
        nameLabel.setText(card.getName());
        effectField.setText(b.getEffect());
        setImages();
    }

    private void setImages() {
        IconImage i = database.grabImage(card, ThingType.CARD);
        imageBox.getChildren().add(new ImageView(i));
    }

    public Boss generateBoss() {
        Boss b = new Boss(card);
        b.setEffect(effectField.getText());
        return b;
    }
}
