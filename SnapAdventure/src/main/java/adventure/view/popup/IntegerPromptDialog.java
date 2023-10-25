package adventure.view.popup;

import adventure.controller.dialog.IntegerPromptDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class IntegerPromptDialog extends AdvDialog<Integer> {

    IntegerPromptDialogController controller;
    public IntegerPromptDialog()
    {
        super();
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("integerPromptDialog.fxml", this.getDialogPane());
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
