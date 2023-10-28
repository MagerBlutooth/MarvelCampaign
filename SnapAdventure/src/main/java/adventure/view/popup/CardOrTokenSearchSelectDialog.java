package adventure.view.popup;

import adventure.controller.dialog.CardOrTokenSearchSelectDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.model.target.Playable;
import snapMain.model.target.TargetList;

public class CardOrTokenSearchSelectDialog extends AdvDialog<Playable> implements Choosable<Playable>{

    CardOrTokenSearchSelectDialogController controller;
    Button okButton;
    public CardOrTokenSearchSelectDialog()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("cardOrTokenSelectDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, TargetList<Playable> selectableCards, String header)
    {
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

    @Override
    public void setChoice(Playable card) {
        controller.setChoice(card);
        enableOKButton();
    }

    @Override
    public void enableOKButton() {
        okButton.setDisable(false);
    }
}
