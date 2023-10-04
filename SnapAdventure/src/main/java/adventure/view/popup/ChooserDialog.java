package adventure.view.popup;

import adventure.controller.ChooserDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class ChooserDialog<T extends SnapTarget> extends Dialog<T> implements Choosable<T> {

    ChooserDialogController<T> controller;
    public ChooserDialog()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("chooserDialog.fxml", this.getDialogPane());
        controller = fxmlAdventureGrabber.getController();
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
    }

    public void initialize(MainDatabase cd, TargetList<T> selectables, TargetType tType)
    {
        controller.initialize(cd, this, selectables, tType);
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
    }
}
