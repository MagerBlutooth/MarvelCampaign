package adventure.view.popup;

import adventure.controller.dialog.AdvMatchResultPopupController;
import adventure.model.stats.AdvMatchResult;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.stage.Window;

public class AdvMatchResultPopup extends AdvDialog<AdvMatchResult> {

    AdvMatchResultPopupController controller;

    public AdvMatchResultPopup()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("matchResultPopup.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(boolean hasCaptain, Window owner)
    {
        super.initialize(owner);
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

    public boolean didSnap()
    {
        return controller.didSnap();
    }
}
