package adventure.controller;

import adventure.model.thing.Boss;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import snapMain.controller.MainDatabase;

import java.util.List;

public class HPDialogController {

    @FXML
    DialogPane dialogPane;
    @FXML
    TextField hpValue;
    @FXML
    ToggleButton gainButton;
    @FXML
    ToggleButton loseButton;

    ToggleGroup toggleGroup;

    int currentHP;
    public void initialize(int h)
    {
        currentHP = h;
        loseButton.setSelected(true);
        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(gainButton, loseButton);

        hpValue.setTextFormatter(new TextFormatter<Integer>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));

    }

    public int getCalculatedHP() {
        if(gainButton.isSelected())
            return currentHP + Integer.parseInt(hpValue.getText());
        return currentHP - Integer.parseInt(hpValue.getText());
    }
}
