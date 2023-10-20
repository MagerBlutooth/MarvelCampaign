package adventure.view.popup;

import adventure.controller.AdvLocationSearchSelectDialogController;
import adventure.model.target.base.AdvLocation;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.model.target.*;

public class AdvLocationSearchSelectDialog extends Dialog<AdvLocation>
        implements Choosable<AdvLocation> {


    Button okButton;
    AdvLocationSearchSelectDialogController controller;
    public AdvLocationSearchSelectDialog()
    {
        FXMLAdventureGrabber fxmlMainGrabber = new FXMLAdventureGrabber();
        fxmlMainGrabber.grabFXML("advLocationSearchSelectDialog.fxml", this.getDialogPane());
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        controller = fxmlMainGrabber.getController();
    }
    public void initialize(MainDatabase cd, TargetList<AdvLocation> selectableLocs)
    {
        controller.initialize(cd, this, selectableLocs);
        okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getChoice();
            }
            return null;
        });
    }

    public void setChoice(AdvLocation loc) {
        controller.setChoice(loc);
        okButton.setDisable(false);
    }

}
