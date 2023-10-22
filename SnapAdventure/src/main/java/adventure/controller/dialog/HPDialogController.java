package adventure.controller.dialog;

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

    int baseHP;
    public void initialize(int h)
    {
        baseHP = h;
        gainButton.setSelected(true);
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

    public int getNewBaseHP() {
        if(gainButton.isSelected())
            return baseHP + Integer.parseInt(hpValue.getText());
        return baseHP - Integer.parseInt(hpValue.getText());
    }
}
