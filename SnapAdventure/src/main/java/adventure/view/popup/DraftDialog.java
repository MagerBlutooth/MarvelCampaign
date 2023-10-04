package adventure.view.popup;

import adventure.controller.ChooserDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.Dialog;
import snapMain.controller.MainDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class DraftDialog extends Dialog<Card> implements Choosable<Card> {

    ChooserDialogController<Card> controller;

    public DraftDialog()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("chooserDialog.fxml", this.getDialogPane());
    }

    public void initialize(MainDatabase cd, TargetList<Card> selectables)
    {
        controller.initialize(cd, this, selectables, TargetType.CARD);
    }

    @Override
    public void setChoice(Card card) {
        controller.setChoice(card);
    }
}
