package adventure.view.popup;

import adventure.controller.dialog.CardSearchSelectDialogController;
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

public class CardSearchSelectDialog extends AdvDialog<ActiveCard> implements Choosable<ActiveCard>{

    CardSearchSelectDialogController controller;
    Button okButton;
    public CardSearchSelectDialog()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("cardSearchSelectDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, TargetList<ActiveCard> selectableCards)
    {
        controller.initialize(cd, this, selectableCards);
        okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getChoice();
            }
            return null;
        });
    }

    @Override
    public void setChoice(ActiveCard card) {
        controller.setChoice(card);
        okButton.setDisable(false);
    }
}
