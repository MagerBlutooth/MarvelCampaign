package adventure.controller.dialog;

import adventure.model.stats.AdvMatchResult;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class AdvMatchResultPopupController {

    @FXML
    CheckBox captainCapture;
    @FXML
    ToggleButton winButton;

    @FXML
    ToggleButton loseButton;

    @FXML
    ToggleButton escapeButton;
    @FXML
    ToggleButton forceRetreatButton;
    @FXML
    ToggleButton tieButton;

    AdvMatchResult result;


    public void setResult(AdvMatchResult r)
    {
        result = r;
    }
    @FXML
    public void setWinResult()
    {
        result = AdvMatchResult.WIN;
    }
    @FXML
    public void setLoseResult()
    {
        result = AdvMatchResult.LOSE;
    }
    @FXML
    public void setEscapeResult()
    {
        result = AdvMatchResult.ESCAPE;
    }

    @FXML
    public void setForceRetreatResult()
    {
        result = AdvMatchResult.FORCE_RETREAT;
    }

    @FXML
    public void setTieResult()
    {
        result = AdvMatchResult.TIE;
    }

    public AdvMatchResult getResult()
    {
        return result;
    }

    public void initialize(boolean hasCaptain) {
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(winButton ,loseButton, escapeButton, forceRetreatButton, tieButton);
        toggleGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        winButton.setSelected(true);
        setWinResult();
        if(!hasCaptain) {
            captainCapture.setOpacity(0.3);
            captainCapture.selectedProperty().addListener((observableValue, aBoolean, t1) -> captainCapture.setSelected(false));
        }
    }

    public boolean doesCapture()
    {
        return captainCapture.isSelected();
    }
}
