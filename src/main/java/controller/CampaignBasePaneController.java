package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import view.button.ButtonToolBar;

public abstract class CampaignBasePaneController extends CampaignPaneController{

    @FXML
    public ButtonToolBar buttonToolBar;

    public abstract void initializeButtonToolBar();

    @Override
    public void initialize(ControllerDatabase controllerDatabase)
    {
        super.initialize(controllerDatabase);
        initializeButtonToolBar();
    }
    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }
}
