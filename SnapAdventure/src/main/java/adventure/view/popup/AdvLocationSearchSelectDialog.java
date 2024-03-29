package adventure.view.popup;

import adventure.controller.dialog.AdvLocationSearchSelectDialogController;
import adventure.model.target.base.AdvLocation;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetList;

public class AdvLocationSearchSelectDialog extends AdvDialog<AdvLocation>
        implements Choosable<AdvLocation> {


    Button okButton;
    AdvLocationSearchSelectDialogController controller;
    public AdvLocationSearchSelectDialog()
    {
        super();
        FXMLAdventureGrabber fxmlMainGrabber = new FXMLAdventureGrabber();
        fxmlMainGrabber.grabFXML("advLocationSearchSelectDialog.fxml", this.getDialogPane());
        controller = fxmlMainGrabber.getController();
    }
    public void initialize(MainDatabase cd, TargetList<AdvLocation> selectableLocs, String header, Window owner)
    {
        super.initialize(owner);
        controller.initialize(cd, this, selectableLocs, header);
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

    @Override
    public void enableOKButton() {
        okButton.setDisable(false);
    }

}
