package adventure.controller;

import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import snapMain.controller.MainDatabase;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class DraftCardDialogController<T extends SnapTarget> extends ChooserDialogController<T> {

    @FXML
    ToggleButton toTeamButton;
    @FXML
    ToggleButton toTempButton;
    ToggleGroup toggleGroup = new ToggleGroup();

    @Override
    public void initialize(MainDatabase md, Choosable<T> dialog, TargetList<T> selectables, TargetType targetType)
    {
        super.initialize(md, dialog, selectables, targetType);
        selection = selectables.get(0);
        toTeamButton.setSelected(true);
        toggleGroup.getToggles().addAll(toTeamButton, toTempButton);
    }

    public boolean isTeam() {
        return toTeamButton.isSelected();
    }
}
