package adventure.view.popup;

import adventure.controller.CardGeneratorDialogController;
import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;

public class CardGeneratorDialog extends Dialog<CardList> {

    CardGeneratorDialogController controller;
    public CardGeneratorDialog()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("cardGeneratorDialog.fxml", this.getDialogPane());
        controller = fxmlAdventureGrabber.getController();
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
    }

    public void initialize(AdvMainDatabase cd, CardList selectables)
    {
        controller.initialize(cd, this, selectables);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getChosen();
            }
            return null;
        });
    }

    public boolean isTeam() {
        return controller.isTeam();
    }
}
