package snapMain.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import snapMain.model.helper.FileHelper;
import snapMain.model.logger.MLogger;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.pane.FullViewPane;
import snapMain.view.pane.BasicStage;

import java.util.logging.FileHandler;

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
    FileHandler fileHandler;

    public void initialize(FullViewPane bP) {

        backPane = bP;
        HBox.setHgrow(spacerPane, Priority.ALWAYS);
    }

    public void initialize(FullViewPane bP, FileHandler f)
    {
        this.initialize(bP);
        fileHandler = f;
    }

    public void removeBackButton()
    {
        buttonToolBar.getChildren().remove(backButton);
    }

    public void goBack() {
        if(fileHandler != null)
            MLogger.LOGGER.removeHandler(fileHandler);
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
