package adventure.view.popup;

import adventure.controller.DeckConstructorDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import snapMain.controller.MainDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class DeckConstructorDialog extends Dialog<CardList> {

    DeckConstructorDialogController controller;

    public DeckConstructorDialog()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("deckConstructorDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, CardList selectables)
    {
        controller.initialize(cd, selectables);
    }
}
