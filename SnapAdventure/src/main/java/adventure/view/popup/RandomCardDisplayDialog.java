package adventure.view.popup;

import adventure.controller.dialog.RandomDisplayDialogController;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetType;

import java.util.ArrayList;

public class RandomCardDisplayDialog extends AdvDialog<ActiveCard> implements Choosable<ActiveCard> {

    RandomDisplayDialogController<ActiveCard> controller;
    Button okButton;

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
        okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true);
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
    protected void centerToParent(Window window) {
        double x = window.getX() + window.getWidth()/8;
        double y = window.getY() + window.getHeight()/8;
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void setChoice(ActiveCard card) {
        controller.setChoice(card);
        enableOKButton();
    }

    @Override
    public void enableOKButton() {
        okButton.setDisable(false);
    }

    public boolean isTeam() {
        return controller.isTeam();
    }
}
