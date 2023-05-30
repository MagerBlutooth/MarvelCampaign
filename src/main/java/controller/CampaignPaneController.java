package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import view.button.ButtonToolBar;
import view.pane.CampaignPane;
import view.pane.CampaignStage;

public abstract class CampaignPaneController {

    protected ControllerDatabase controllerDatabase;
    public abstract Scene getCurrentScene();

    public void changeScene(CampaignPane cPane)
    {
        CampaignStage primaryWindow = (CampaignStage) getCurrentScene().getWindow();
        primaryWindow.initialize(cPane);
        primaryWindow.centerOnScreen();
    }

    public void initialize(ControllerDatabase controllerDatabase)
    {
        this.controllerDatabase = controllerDatabase;
    }

    public ControllerDatabase getDatabase() {
        return controllerDatabase;
    }
}
