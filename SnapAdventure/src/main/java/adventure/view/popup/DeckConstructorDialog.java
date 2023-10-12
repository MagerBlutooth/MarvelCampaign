package adventure.view.popup;

import adventure.controller.DeckConstructorDialogController;
import adventure.model.stats.MatchResult;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
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
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UNDECORATED);
    }

    public void initialize(MainDatabase cd, CardList selectables)
    {
        controller.initialize(cd, selectables);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getDeck();
            }
            return null;
        });
    }

    public MatchResult getMatchResult() {
        return controller.getMatchResult();
    }
}
