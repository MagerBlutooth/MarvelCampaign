package adventure.view.popup;

import adventure.controller.dialog.ChooserDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class ChooserDialog<T extends SnapTarget> extends AdvDialog<T> implements Choosable<T> {

    ChooserDialogController<T> controller;
    Button okButton;
    public ChooserDialog()
    {
        super();
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("chooserDialog.fxml", this.getDialogPane());
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, TargetList<T> selectables, TargetType tType)
    {
        controller.initialize(cd, this, selectables, tType);
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
    public void setChoice(T subject) {
        controller.setChoice(subject);
        okButton.setDisable(false);
    }
}
