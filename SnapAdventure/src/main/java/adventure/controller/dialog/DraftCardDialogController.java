package adventure.controller.dialog;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class DraftCardDialogController extends SimpleChooserDialogController<ActiveCard> {

    @FXML
    Button viewTeamButton;
    @FXML
    ToggleButton toTeamButton;
    @FXML
    ToggleButton toTempButton;
    ToggleGroup toggleGroup = new ToggleGroup();

    public void initialize(MainDatabase md, Choosable<ActiveCard> dialog, TargetList<ActiveCard> selectables,
                           ActiveCardList team, TargetType targetType)
    {
        super.initialize(md, dialog, selectables, team, targetType);
        selection = selectables.get(0);
        toTeamButton.setSelected(true);
        toggleGroup.getToggles().addAll(toTeamButton, toTempButton);
    }

    public boolean isTeam() {
        return toTeamButton.isSelected();
    }
}
