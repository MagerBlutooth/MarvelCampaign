package adventure.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import snapMain.view.pane.FullViewPane;

public class LogViewPaneController extends FullViewPaneController {

    @FXML
    public ScrollPane logScrollPane;

    @FXML
    public Button backButton;

    FullViewPane backPane;

    @Override
    public Scene getCurrentScene() {
        return backButton.getScene();
    }

    @Override
    public void initializeButtonToolBar() {

    }

    public void initialize(TextArea textArea, FullViewPane back)
    {
        backPane = back;
        logScrollPane.setContent(textArea);
    }

    @FXML
    public void goBack()
    {
        changeScene(backPane);
    }
}
