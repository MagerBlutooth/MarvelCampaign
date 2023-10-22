package adventure.view.popup;

import adventure.controller.dialog.DraftCardDialogController;
import adventure.model.target.ActiveCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class DraftDialog extends AdvDialog<ActiveCard> implements Choosable<ActiveCard> {

    DraftCardDialogController controller;
    Button okButton;

    public DraftDialog()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("draftDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, TargetList<ActiveCard> selectables)
    {
        controller.initialize(cd, this, selectables, TargetType.CARD);
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
    public void setChoice(ActiveCard card) {
        controller.setChoice(card);
        okButton.setDisable(false);
    }

    public boolean isTeam() {
        return controller.isTeam();
    }
}
