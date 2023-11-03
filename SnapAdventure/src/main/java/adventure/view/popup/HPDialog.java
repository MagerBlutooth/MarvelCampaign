package adventure.view.popup;

import adventure.controller.dialog.HPDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;

public class HPDialog extends AdvDialog<Integer> {

    HPDialogController controller;

    public HPDialog()
    {
        super();
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("hpDialog.fxml", this.getDialogPane());
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(int currentHP)
    {
        controller.initialize(currentHP);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getNewBaseHP();
            }
            return null;
        });
    }


}
