package campaign.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import campaign.view.pane.BasicPane;
import campaign.view.pane.BasicStage;

public class ButtonToolBarController {

    @FXML
    Button backButton;
    @FXML
    Pane spacerPane;
    @FXML
    Button exitButton;
    BasicPane backPane;

    public void initialize(BasicPane bP) {

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

    public void changeScene(BasicPane cPane)
    {
        BasicStage primaryWindow = (BasicStage) getCurrentScene().getWindow();
        primaryWindow.initialize(cPane);
        primaryWindow.centerOnScreen();
    }

    private Scene getCurrentScene() {
        return backButton.getScene();
    }
}
