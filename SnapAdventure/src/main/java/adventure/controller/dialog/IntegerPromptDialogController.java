package adventure.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.Random;

public class IntegerPromptDialogController {

    @FXML
    ChoiceBox<Integer> integerChoices;
    @FXML
    Label promptLabel;

     public Integer randomValue() {
        Random random = new Random();
        int randomInt = random.nextInt(integerChoices.getItems().get(0), integerChoices.getItems().size());
        return randomInt;
    }

    public Integer getChoice() {
         return integerChoices.getValue();
    }

    public void initialize(String prompt, int min, int max) {
        promptLabel.setText(prompt);
        for(int i = min; i <= max; i++)
        {
            integerChoices.getItems().add(i);
        }
        integerChoices.setValue(min);
    }
}
