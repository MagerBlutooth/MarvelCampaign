package adventure.view.popup;

import adventure.controller.IntegerPromptDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.util.Random;

public class IntegerPromptDialog extends Dialog<Integer> {

    IntegerPromptDialogController controller;
    public IntegerPromptDialog()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("integerPromptDialog.fxml", this.getDialogPane());
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(String prompt, int min, int max)
    {
        controller.initialize(prompt, min, max);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getChoice();
            }
            else if(dialogButton.getButtonData() == ButtonBar.ButtonData.OTHER)
                return controller.randomValue();
            return null;
        });
    }
}
