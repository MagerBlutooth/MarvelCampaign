package adventure.view.popup;

import adventure.controller.dialog.CardGeneratorDialogController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.ActiveCardList;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class CardGeneratorDialog extends AdvDialog<ActiveCardList> {

    CardGeneratorDialogController controller;
    public CardGeneratorDialog()
    {
        super();
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("cardGeneratorDialog.fxml", this.getDialogPane());
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase cd, ActiveCardList selectables, Window window)
    {
        super.initialize(window);
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
