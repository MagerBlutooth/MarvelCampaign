package adventure.view.popup;

import adventure.controller.HPDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class HPDialog extends Dialog<Integer> {

    HPDialogController controller;

    public HPDialog()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("hpDialog.fxml", this.getDialogPane());
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(int currentHP)
    {
        controller.initialize(currentHP);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getCalculatedHP();
            }
            return null;
        });
    }


}
