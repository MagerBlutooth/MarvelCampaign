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

    public void initialize(MainDatabase md, ActiveCardList exhaustedCards, ActiveCardList recoveredCards, Parent root)
    {
        super.initialize(root);
        controller.initialize(md, exhaustedCards, recoveredCards);
    }

    @Override
    protected void centerToParent(Window window) {
        double x = window.getX() + window.getWidth()/8;
        double y = window.getY() + window.getHeight()/8;
        this.setX(x);
        this.setY(y);
    }

}
