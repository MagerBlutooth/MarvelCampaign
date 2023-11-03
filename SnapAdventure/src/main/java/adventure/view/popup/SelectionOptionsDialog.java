package adventure.view.popup;

import adventure.controller.SelectionOptionDialogController;
import adventure.model.target.ActiveCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.ButtonBar;
import javafx.stage.Window;
import snapMain.model.target.TargetList;

public class SelectionOptionsDialog extends AdvDialog<TargetList<ActiveCard>> {

    SelectionOptionDialogController controller;

    public SelectionOptionsDialog()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("selectionOptionsDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(TargetList<ActiveCard> freeAgents, boolean multiSelect, Window owner)
    {
        super.initialize(owner);
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
