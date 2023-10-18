package adventure.view.popup;

import adventure.controller.SelectionOptionDialogController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import snapMain.model.target.Card;
import snapMain.model.target.TargetList;

import java.util.Objects;

public class SelectionOptionsDialog extends Dialog<TargetList<Card>> {

    SelectionOptionDialogController controller;

    public SelectionOptionsDialog()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("selectionOptionsDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
        initStyle(StageStyle.UNDECORATED);
    }

    public void initialize(TargetList<Card> freeAgents, boolean multiSelect)
    {
        controller.initialize(freeAgents, multiSelect);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getSelectables();
            }
            return null;
        });
    }

    public boolean isMutiple() {
        return controller.isMultiple();
    }

    public int getNumber()
    {
        return controller.getSelectionCount();
    }
}
