package adventure.view.popup;

import adventure.controller.CardGainSearchSelectDialogController;
import adventure.model.target.ActiveCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetList;

public class CardGainSearchSelectDialog extends Dialog<ActiveCard> implements Choosable<ActiveCard>{

    CardGainSearchSelectDialogController controller;
    public CardGainSearchSelectDialog()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("addCardSearchSelectDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
        initStyle(StageStyle.UNDECORATED);
        initModality(Modality.APPLICATION_MODAL);
    }

    public void initialize(MainDatabase cd, TargetList<ActiveCard> selectableCards)
    {
        controller.initialize(cd, this, selectableCards);

        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getChoice();
            }
            return null;
        });
    }

    public boolean isTeam() {
        return controller.isTeam();
    }

    @Override
    public void setChoice(ActiveCard card) {
        controller.setChoice(card);
    }
}
