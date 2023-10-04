package adventure.controller;

import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import snapMain.controller.MainDatabase;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class DraftCardChooserDialogController<T extends SnapTarget> extends ChooserDialogController<T> {

    @FXML
    ToggleButton toTeamButton;
    @FXML
    ToggleButton toTempButton;
    ToggleGroup toggleGroup;

    @Override
    public void initialize(MainDatabase md, Choosable<T> dialog, TargetList<T> selectables, TargetType targetType)
    {
        super.initialize(md, dialog, selectables, targetType);
        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(toTeamButton, toTempButton);
        toTeamButton.setSelected(true);
    }

    public boolean isTeam() {
        return toTeamButton.isSelected();
    }
}
