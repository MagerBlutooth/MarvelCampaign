package snapMain.controller.editor;

import snapMain.controller.MainDatabase;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Card;
import snapMain.model.target.CardAttribute;
import snapMain.view.grabber.IconConstant;

import java.util.ArrayList;
import java.util.List;

public class CardEditorNodeController extends BasicNodeController {

    MainDatabase database;
    Card card;
    @FXML
    TextField nameField;
    @FXML
    ChoiceBox<Integer> costChoice;
    @FXML
    ChoiceBox<Integer> powerChoice;
    @FXML
    ChoiceBox<Integer> poolChoice;
    @FXML
    TextArea effectField;
    @FXML
    ImageView costImage;
    @FXML
    ImageView powerImage;
    @FXML
    ListView<CardAttribute> cardCheckList;

    public void initialize(MainDatabase d, Card c) {
        database = d;
        card = c;
        nameField.setText(c.getName());
        setCostValues();
        setPowerValues();
        setPoolValues();
        effectField.setText(c.getEffect());
        setCardCheckList();
        setImages();
    }

    private void setImages() {
        costImage.setImage(database.grabIcon(IconConstant.COST));
        costImage.setFitHeight(50);
        costImage.setFitWidth(50);
        powerImage.setFitHeight(50);
        powerImage.setFitWidth(50);
        powerImage.setImage(database.grabIcon(IconConstant.POWER));
    }

    private void setCardCheckList() {
        for(CardAttribute attribute: CardAttribute.values())
        {
            cardCheckList.getItems().add(attribute);
        }
        cardCheckList.setCellFactory(CheckBoxListCell.forListView(att -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.setValue(card.hasAttribute(att.toString()));
            observable.addListener((obs, wasSelected, isNowSelected) ->
                    card.setAttribute(att, isNowSelected));
            return observable;
        }));
    }

    private void setCostValues() {
        List<Integer> costOptions = new ArrayList<>();
        for(int i = SnapMainConstants.MIN_COST; i <= SnapMainConstants.MAX_COST; i++)
        {
            costOptions.add(i);
        }
        costChoice.setItems(FXCollections.observableList(costOptions));
        costChoice.getSelectionModel().select((Integer)card.getCost());
    }

    private void setPowerValues()
    {
        List<Integer> powerOptions = new ArrayList<>();
        for(int i = SnapMainConstants.MIN_POWER; i <= SnapMainConstants.MAX_POWER; i++)
        {
            powerOptions.add(i);
        }
        powerChoice.setItems(FXCollections.observableList(powerOptions));
        powerChoice.getSelectionModel().select((Integer)card.getPower());
    }

    private void setPoolValues()
    {
        List<Integer> poolOptions = new ArrayList<>();
        for(int i = SnapMainConstants.MIN_POOL; i <= SnapMainConstants.MAX_POOL; i++)
        {
            poolOptions.add(i);
        }
        poolChoice.setItems(FXCollections.observableList(poolOptions));
        poolChoice.getSelectionModel().select((Integer)card.getPool());
    }

    public Card generateCard() {
        Card c = card;
        c.setName(nameField.getText());
        c.setCost(costChoice.getSelectionModel().getSelectedItem());
        c.setPower(powerChoice.getSelectionModel().getSelectedItem());
        c.setPool(poolChoice.getSelectionModel().getSelectedItem());
        c.setEffect(effectField.getText());
        return c;
    }
}
