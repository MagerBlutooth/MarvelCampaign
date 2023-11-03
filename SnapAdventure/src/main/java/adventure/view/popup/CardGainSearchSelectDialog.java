package adventure.view.popup;

import adventure.controller.dialog.CardGainSearchSelectDialogController;
import adventure.model.target.ActiveCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetList;

public class CardGainSearchSelectDialog extends AdvDialog<ActiveCard> implements Choosable<ActiveCard>{

    CardGainSearchSelectDialogController controller;
    Button okButton;
    public CardGainSearchSelectDialog()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("addCardSearchSelectDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, TargetList<ActiveCard> selectableCards, String header, Window window)
    {
        super.initialize(window);
        controller.initialize(cd, this, selectableCards, header);
        okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true);
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
        enableOKButton();
    }

    @Override
    public void enableOKButton() {
        okButton.setDisable(false);
    }
}
