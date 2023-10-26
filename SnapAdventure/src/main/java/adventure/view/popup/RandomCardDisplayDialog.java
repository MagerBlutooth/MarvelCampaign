package adventure.view.popup;

import adventure.controller.dialog.RandomDisplayDialogController;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetType;

import java.util.ArrayList;

public class RandomCardDisplayDialog extends AdvDialog<ActiveCard> implements Choosable<ActiveCard> {

    RandomDisplayDialogController<ActiveCard> controller;

    public RandomCardDisplayDialog()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("randomDisplayDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, ActiveCard selection, String header)
    {
        ActiveCardList selectionArray = new ActiveCardList(new ArrayList<>());
        selectionArray.add(selection);
        controller.initialize(cd, this, selectionArray, TargetType.CARD, header);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getSelection();
            }
            return null;
        });
    }

    @Override
    public void setChoice(ActiveCard card) {
        controller.setChoice(card);
    }

    public boolean isTeam() {
        return controller.isTeam();
    }
}
