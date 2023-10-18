package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.Difficulty;
import adventure.model.adventure.Adventure;
import adventure.view.pane.AdvNewProfilePane;
import adventure.view.pane.AdvStartPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import snapMain.view.button.ButtonToolBar;

public class AdvNewProfileOptionsPaneController extends AdvPaneController {

    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    ChoiceBox<Integer> teamMemberOptions;
    @FXML
    ChoiceBox<Integer> teamCaptainOptions;
    @FXML
    HBox difficultyBox;
    Difficulty difficulty;

    Adventure adventure;
    AdvStartPane backPane;
    ToggleGroup difficultySelection;

    public void initialize(AdvMainDatabase mD, Adventure a, AdvStartPane sPane)
    {
        mainDatabase = mD;
        adventure = a;
        backPane = sPane;
        difficultySelection = new ToggleGroup();
        initializeButtonToolBar();
        initializeTeamMemberOptions();
        initializeTeamCaptainOptions();
        initializeDifficultyBox();
    }
    private void initializeDifficultyBox() {
        ToggleGroup toggleGroup = new ToggleGroup();
        for(Difficulty d: Difficulty.values())
        {
            ToggleButton t = new ToggleButton(d.name());
            t.setOnAction(e -> setDifficulty(t.getText()));
            toggleGroup.getToggles().add(t);
            difficultyBox.getChildren().add(t);
        }
        toggleGroup.getToggles().get(1).setSelected(true);
        setDifficulty(Difficulty.NORMAL.toString());
        toggleGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
    }

    private void setDifficulty(String text) {
        difficulty = Difficulty.valueOf(text);
    }

    private void initializeTeamMemberOptions() {
        for(Integer i: AdventureConstants.TEAM_MEMBER_START_CHOICES)
        {
            teamMemberOptions.getItems().add(i);
        }
        teamMemberOptions.setValue(teamMemberOptions.getItems().get(1));
    }


    private void initializeTeamCaptainOptions() {
        for(Integer i: AdventureConstants.TEAM_CAPTAIN_START_CHOICES)
        {
            teamCaptainOptions.getItems().add(i);
        }
        teamCaptainOptions.setValue(teamCaptainOptions.getItems().get(2));
    }

    public void showNewProfile()
    {
        adventure.initialize(mainDatabase, teamMemberOptions.getValue(), teamCaptainOptions.getValue(),
                difficulty);
        AdvNewProfilePane advNewProfilePane = new AdvNewProfilePane();
        advNewProfilePane.initialize(mainDatabase, adventure, backPane);
        changeScene(advNewProfilePane);
    }

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        buttonToolBar.initialize(backPane);
    }
}
