package adventure.view.popup;

import adventure.controller.dialog.AdvMatchResultPopupController;
import adventure.model.stats.AdvMatchResult;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar;

public class AdvMatchResultPopup extends AdvDialog<AdvMatchResult> {

    AdvMatchResultPopupController controller;

    public AdvMatchResultPopup()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("matchResultPopup.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(boolean hasCaptain, Parent root)
    {
        super.initialize(root);
        controller.initialize(hasCaptain);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getResult();
            }
            return null;
        });
    }

    public boolean doesCapture() {
        return controller.doesCapture();
    }
}
