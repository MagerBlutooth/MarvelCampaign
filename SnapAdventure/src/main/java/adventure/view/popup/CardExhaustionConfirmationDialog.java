package adventure.view.popup;

import adventure.controller.dialog.CardExhaustionConfirmationDialogController;
import adventure.model.target.ActiveCardList;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import snapMain.controller.MainDatabase;

public class CardExhaustionConfirmationDialog extends AdvDialog<Boolean> {

    CardExhaustionConfirmationDialogController controller;
    public CardExhaustionConfirmationDialog()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("cardExhaustConfirmDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase md, ActiveCardList exhaustedCards, ActiveCardList recoveredCards, Window window)
    {
        super.initialize(window);
        controller.initialize(md, exhaustedCards, recoveredCards);
    }

}
