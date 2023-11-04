package adventure.view.popup;

import adventure.controller.dialog.ChooserDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;
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

    public void initialize(MainDatabase cd, TargetList<T> selectables, TargetType tType, String header, Window window)
    {
        super.initialize(window);
        controller.initialize(cd, this, selectables, tType, header);
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
        enableOKButton();
    }

    @Override
    public void enableOKButton() {
        okButton.setDisable(false);
    }
}
