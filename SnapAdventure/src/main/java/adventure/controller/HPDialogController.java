package adventure.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
