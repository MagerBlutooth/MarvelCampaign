package adventure.view.popup;

import adventure.controller.dialog.SimpleChooserDialogController;
import adventure.controller.dialog.WoundCaptureChoiceDialogController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.ActiveCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class WoundCaptureChoiceDialog extends AdvDialog<ActiveCard> implements Choosable<ActiveCard> {

    WoundCaptureChoiceDialogController controller;
    Button okButton;

    public WoundCaptureChoiceDialog()
    {
        super();
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("woundCaptureChoiceDialog.fxml", this.getDialogPane());
        controller = fxmlAdventureGrabber.getController();
    }
    public void initialize(AdvMainDatabase mainDatabase, TargetList<ActiveCard> things)
    {
        controller.initialize(mainDatabase, this, things);
        okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getSelection();
            }
            return null;
        });
    }

    @Override
    public void setChoice(ActiveCard subject) {
        controller.setChoice(subject);
        okButton.setDisable(false);
    }

    public boolean woundOptionSelected()
    {
        return controller.woundOptionSelected();
    }
    public boolean captureOptionSelected()
    {
        return controller.captureOptionSelected();
    }
}
