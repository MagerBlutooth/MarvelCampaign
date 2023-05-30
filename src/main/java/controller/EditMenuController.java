package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import view.pane.MainMenuPane;
import view.pane.manager.CardManagerPane;
import view.pane.manager.LocationManagerPane;
import view.pane.manager.TokenManagerPane;

public class EditMenuController extends CampaignBasePaneController {
    @FXML
    Button cardEditorButton;
    @FXML
    Button tokenEditorButton;
    @FXML
    Button locationEditorButton;

    @FXML
    public void cardEditor()
    {
        CardManagerPane cardManagerPane = new CardManagerPane();
        cardManagerPane.initialize(controllerDatabase);
        changeScene(cardManagerPane);
    }
    @FXML
    public void locationEditor() {
        LocationManagerPane locationManagerPane = new LocationManagerPane();
        locationManagerPane.initialize(controllerDatabase);
        changeScene(locationManagerPane);}

    @FXML
    public void tokenEditor()
    {
        TokenManagerPane tokenManagerPane = new TokenManagerPane();
        tokenManagerPane.initialize(controllerDatabase);
        changeScene(tokenManagerPane);
    }

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
    public void initialize(ControllerDatabase database) {
        super.initialize(database);
    }
}
