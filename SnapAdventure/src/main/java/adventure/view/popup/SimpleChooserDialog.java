package adventure.view.popup;

import adventure.controller.dialog.SimpleChooserDialogController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.ActiveCard;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import snapMain.model.target.Card;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class SimpleChooserDialog<T extends SnapTarget> extends AdvDialog<T> implements Choosable<T> {

    SimpleChooserDialogController<T> controller;
    Button okButton;

    public SimpleChooserDialog()
    {
        super();
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("simpleChooserDialog.fxml", this.getDialogPane());
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase mainDatabase, TargetList<T> things, TargetType type)
    {
        controller.initialize(mainDatabase, this, things, type);
        primeOKButton();
    }
    public void initialize(AdvMainDatabase mainDatabase, TargetList<T> things, TargetType type, String header)
    {
        controller.initialize(mainDatabase, this, things, type, header);
        primeOKButton();

    }

    private void primeOKButton() {
        okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true);
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
    public void setChoice(T subject) {
        controller.setChoice(subject);
        enableOKButton();
    }

    @Override
    public void enableOKButton() {
        okButton.setDisable(false);
    }
}
