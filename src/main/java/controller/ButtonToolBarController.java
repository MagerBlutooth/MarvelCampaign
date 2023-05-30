package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import view.pane.CampaignPane;
import view.pane.CampaignStage;

public class ButtonToolBarController {

    @FXML
    Button backButton;
    @FXML
    Pane spacerPane;
    @FXML
    Button exitButton;
    CampaignPane backPane;

    public void initialize(CampaignPane bP) {

        backPane = bP;
        HBox.setHgrow(spacerPane, Priority.ALWAYS);
    }

    public void goBack() {
        changeScene(backPane);
    }

    public void exit()
    {
        Platform.exit();
    }

    public void changeScene(CampaignPane cPane)
    {
        CampaignStage primaryWindow = (CampaignStage) getCurrentScene().getWindow();
        primaryWindow.initialize(cPane);
        primaryWindow.centerOnScreen();
    }

    private Scene getCurrentScene() {
        return backButton.getScene();
    }
}
