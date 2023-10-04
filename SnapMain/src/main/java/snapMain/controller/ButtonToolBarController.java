package snapMain.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.pane.FullViewPane;
import snapMain.view.pane.BasicStage;

public class ButtonToolBarController {

    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    Button backButton;
    @FXML
    Pane spacerPane;
    @FXML
    Button exitButton;
    FullViewPane backPane;

    public void initialize(FullViewPane bP) {

        backPane = bP;
        HBox.setHgrow(spacerPane, Priority.ALWAYS);
    }

    public void removeBackButton()
    {
        buttonToolBar.getChildren().remove(backButton);
    }

    public void goBack() {
        changeScene(backPane);
    }

    public void exit()
    {
        Platform.exit();
    }

    public void changeScene(FullViewPane cPane)
    {
        BasicStage primaryWindow = (BasicStage) getCurrentScene().getWindow();
        primaryWindow.initialize(cPane);
        primaryWindow.centerOnScreen();
    }

    private Scene getCurrentScene() {
        return backButton.getScene();
    }
}
