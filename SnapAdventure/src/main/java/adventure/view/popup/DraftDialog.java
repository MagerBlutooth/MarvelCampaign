package adventure.view.popup;

import adventure.controller.DraftCardDialogController;
import adventure.model.target.ActiveCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class DraftDialog extends Dialog<ActiveCard> implements Choosable<ActiveCard> {

    DraftCardDialogController controller;

    public DraftDialog()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("draftDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, TargetList<ActiveCard> selectables)
    {
        controller.initialize(cd, this, selectables, TargetType.CARD);
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
    }

    public boolean isTeam() {
        return controller.isTeam();
    }
}
