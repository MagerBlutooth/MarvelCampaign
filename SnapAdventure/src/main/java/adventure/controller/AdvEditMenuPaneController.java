package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.view.pane.AdvCardManagerPane;
import adventure.view.pane.AdvLocationManagerPane;
import adventure.view.pane.AdvMainMenuPane;
import adventure.view.pane.AdvTokenManagerPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import snapMain.controller.ButtonToolBarPaneController;

public class AdvEditMenuPaneController extends ButtonToolBarPaneController<AdvMainDatabase> {
    @FXML
    Button cardEditorButton;
    @FXML
    Button locationEditorButton;

    @FXML
    Button tokenEditorButton;

    @FXML
    public void cardEditor()
    {
        AdvCardManagerPane cardManagerPane = new AdvCardManagerPane();
        cardManagerPane.initialize(mainDatabase);
        changeScene(cardManagerPane);
    }
    @FXML
    public void locationEditor() {
        AdvLocationManagerPane locationManagerPane = new AdvLocationManagerPane();
        locationManagerPane.initialize(mainDatabase);
        changeScene(locationManagerPane);}

    @FXML
    public void tokenEditor() {
        AdvTokenManagerPane tokenManagerPane = new AdvTokenManagerPane();
        tokenManagerPane.initialize(mainDatabase);
        changeScene(tokenManagerPane);}

    @Override
    public Scene getCurrentScene() {
        return cardEditorButton.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        AdvMainMenuPane mainMenuPane = new AdvMainMenuPane();
        mainMenuPane.initialize(mainDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    @Override
    public void initialize(AdvMainDatabase database) {
        super.initialize(database);
    }
}
