package adventure.view.popup;

import adventure.controller.DraftCardChooserDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import snapMain.controller.MainDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class DraftDialog extends Dialog<Card> implements Choosable<Card> {

    DraftCardChooserDialogController<Card> controller;

    public DraftDialog()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("draftDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, TargetList<Card> selectables)
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
    public void setChoice(Card card) {
        controller.setChoice(card);
    }

    public boolean isTeam() {
        return controller.isTeam();
    }
}
