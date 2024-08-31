package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.target.base.AdvToken;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import snapMain.controller.editor.BasicNodeController;
import snapMain.model.target.Token;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.thing.TokenView;

public class AdvTokenEditorNodeController extends BasicNodeController<AdvMainDatabase, Token> {

    @FXML
    TokenView imageView;
    @FXML
    TextArea effectField;
    AdvMainDatabase database;
    Token card;

    public void initialize(AdvMainDatabase d, AdvToken t) {
        database = d;
        card = t.getToken();
        effectField.setText(t.getEffect());
        setImages();
    }

    private void setImages() {
        IconImage i = database.grabImage(card);
        imageView.setImage(i, ViewSize.GIANT);
    }

    public AdvToken generateToken() {
        AdvToken t = new AdvToken(card);
        t.setEffect(effectField.getText());
        return t;
    }
}
