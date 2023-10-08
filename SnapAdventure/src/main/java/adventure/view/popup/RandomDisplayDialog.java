package adventure.view.popup;

import adventure.controller.DraftCardDialogController;
import adventure.controller.RandomDisplayDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import snapMain.controller.MainDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

import java.util.ArrayList;

public class RandomDisplayDialog extends Dialog<Card> implements Choosable<Card> {

    RandomDisplayDialogController<Card> controller;

    public RandomDisplayDialog()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("randomDisplayDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, Card selection)
    {
        CardList selectionArray = new CardList(new ArrayList<>());
        selectionArray.add(selection);
        controller.initialize(cd, this, selectionArray, TargetType.CARD);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getSelection();
            }
            return null;
        });
    }

    @Override
    public void setChoice(Card card) {
        controller.setChoice(card);
    }

    public boolean isTeam() {
        return controller.isTeam();
    }
}
