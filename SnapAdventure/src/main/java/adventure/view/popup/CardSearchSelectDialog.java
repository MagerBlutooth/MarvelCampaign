package adventure.view.popup;

import adventure.controller.dialog.CardSearchSelectDialogController;
import adventure.model.target.ActiveCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetList;

public class CardSearchSelectDialog extends AdvDialog<ActiveCard> implements Choosable<ActiveCard>{

    CardSearchSelectDialogController controller;
    Button okButton;
    public CardSearchSelectDialog()
    {
        super();
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("cardSearchSelectDialog.fxml", this.getDialogPane());
        controller = adventureGrabber.getController();
    }

    public void initialize(MainDatabase cd, TargetList<ActiveCard> selectableCards, String header, Parent root)
    {
        super.initialize(root);
        controller.initialize(cd, this, selectableCards, header);
        okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true);
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getChoice();
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
}
