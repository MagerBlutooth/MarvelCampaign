package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.view.pane.AdvBossManagerPane;
import adventure.view.pane.AdvLocationManagerPane;
import adventure.view.pane.AdvMainMenuPane;
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
    public void cardEditor()
    {
        AdvBossManagerPane cardManagerPane = new AdvBossManagerPane();
        cardManagerPane.initialize(mainDatabase);
        changeScene(cardManagerPane);
    }
    @FXML
    public void locationEditor() {
        AdvLocationManagerPane locationManagerPane = new AdvLocationManagerPane();
        locationManagerPane.initialize(mainDatabase);
        changeScene(locationManagerPane);}

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
