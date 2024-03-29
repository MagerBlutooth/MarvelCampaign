package adventure.view.popup;

import adventure.controller.dialog.SimpleChooserDialogController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.ActiveCardList;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;
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

    public void initialize(AdvMainDatabase mainDatabase, TargetList<T> things, ActiveCardList team, TargetType type)
    {
        controller.initialize(mainDatabase, this, things, team, type);
        primeOKButton();
    }
    public void initialize(AdvMainDatabase mainDatabase, TargetList<T> things, ActiveCardList team,
                           TargetType type, String header, Window owner)
    {
        super.initialize(owner);
        controller.initialize(mainDatabase, this, things, team, type, header);
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
    public void setChoice(T subject) {
        controller.setChoice(subject);
        enableOKButton();
    }

    @Override
    public void enableOKButton() {
        okButton.setDisable(false);
    }
}
