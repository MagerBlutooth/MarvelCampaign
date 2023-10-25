package adventure.view.popup;

import adventure.controller.dialog.CardOrTokenSearchSelectDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.model.target.Playable;
import snapMain.model.target.TargetList;

public class CardOrTokenSearchSelectDialog extends AdvDialog<Playable> implements Choosable<Playable>{

    CardOrTokenSearchSelectDialogController controller;
    public CardOrTokenSearchSelectDialog()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("cardOrTokenSelectDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, TargetList<Playable> selectableCards)
    {
        controller.initialize(cd, this, selectableCards);

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
    }
}
