package adventure.view.popup;

import adventure.controller.CardExhaustionConfirmationDialogController;
import adventure.controller.CardSearchSelectDialogController;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetList;

public class CardExhaustionConfirmationDialog extends Dialog<Boolean> {

    CardExhaustionConfirmationDialogController controller;
    public CardExhaustionConfirmationDialog()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("cardExhaustConfirmDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
        initStyle(StageStyle.UNDECORATED);
        initModality(Modality.APPLICATION_MODAL);
    }

    public void initialize(MainDatabase md, ActiveCardList exhaustedCards, ActiveCardList recoveredCards)
    {
        controller.initialize(md, exhaustedCards, recoveredCards);
    }
}
