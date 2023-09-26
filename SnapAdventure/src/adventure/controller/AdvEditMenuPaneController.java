package adventure.controller;

import adventure.model.AdvControllerDatabase;
import adventure.view.pane.AdvCardManagerPane;
import adventure.view.pane.AdvLocationManagerPane;
import campaign.controller.ButtonToolBarPaneController;
import campaign.view.pane.MainMenuPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class AdvEditMenuPaneController extends ButtonToolBarPaneController<AdvControllerDatabase> {
    @FXML
    Button cardEditorButton;
    @FXML
    Button locationEditorButton;

    @FXML
    public void cardEditor()
    {
        AdvCardManagerPane cardManagerPane = new AdvCardManagerPane();
        cardManagerPane.initialize(controllerDatabase);
        changeScene(cardManagerPane);
    }
    @FXML
    public void locationEditor() {
        AdvLocationManagerPane locationManagerPane = new AdvLocationManagerPane();
        locationManagerPane.initialize(controllerDatabase);
        changeScene(locationManagerPane);}

    @Override
    public Scene getCurrentScene() {
        return cardEditorButton.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        MainMenuPane mainMenuPane = new MainMenuPane();
        mainMenuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    @Override
    public void initialize(AdvControllerDatabase database) {
        super.initialize(database);
    }
}
