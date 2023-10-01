package snapMain.controller.editor;

import snapMain.controller.MainDatabase;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import snapMain.model.constants.CampaignConstants;
import snapMain.model.thing.Token;

import java.util.ArrayList;
import java.util.List;

public class TokenEditorNodeController extends BasicNodeController<MainDatabase, Token> {

    Token token;
    @FXML
    TextField nameField;
    @FXML
    ChoiceBox<Integer> costChoice;
    @FXML
    ChoiceBox<Integer> powerChoice;

    @Override
    public void initialize(MainDatabase d, Token t) {
        database = d;
        token = t;
        nameField.setText(t.getName());
        setCostValues();
        setPowerValues();
    }

    private void setCostValues() {
        List<Integer> costOptions = new ArrayList<>();
        for(int i = CampaignConstants.MIN_COST; i < CampaignConstants.MAX_COST; i++)
        {
            costOptions.add(i);
        }
        costChoice.setItems(FXCollections.observableList(costOptions));
        costChoice.getSelectionModel().select(token.getCost());
    }

    private void setPowerValues()
    {
        List<Integer> powerOptions = new ArrayList<>();
        for(int i = CampaignConstants.MIN_POWER; i < CampaignConstants.MAX_POWER; i++)
        {
            powerOptions.add(i);
        }
        powerChoice.setItems(FXCollections.observableList(powerOptions));
        powerChoice.getSelectionModel().select((Integer)token.getPower());
    }

    public Token generateToken() {
        Token t = token;
        t.setName(nameField.getText());
        t.setCost(costChoice.getValue());
        t.setPower(powerChoice.getValue());
        return t;
    }
}
