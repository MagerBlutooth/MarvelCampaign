package adventure.view.popup;

import adventure.controller.AdvLocationSearchSelectDialogController;
import adventure.controller.AdvSearchSelectDialogController;
import adventure.controller.ChooserDialogGridActionController;
import adventure.model.thing.AdvLocation;
import adventure.model.thing.AdvLocationList;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.DialogGridActionController;
import snapMain.model.target.*;
import snapMain.view.ViewSize;
import snapMain.view.dialog.SearchSelectDialog;
import snapMain.view.fxml.FXMLMainGrabber;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

import java.util.ArrayList;

public class AdvLocationSearchSelectDialog<T extends AdvLocation> extends Dialog<AdvLocation>
        implements Choosable<AdvLocation> {


    AdvLocationSearchSelectDialogController controller;
    public AdvLocationSearchSelectDialog()
    {
        FXMLAdventureGrabber fxmlMainGrabber = new FXMLAdventureGrabber();
        fxmlMainGrabber.grabFXML("advLocationSearchSelectDialog.fxml", this.getDialogPane());
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        controller = fxmlMainGrabber.getController();
    }
    public void initialize(MainDatabase cd, TargetList<AdvLocation> selectableLocs)
    {
        controller.initialize(cd, this, selectableLocs);

        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return controller.getChoice();
            }
            return null;
        });
    }

    public void setChoice(AdvLocation loc) {
        controller.setChoice(loc);
    }

}
