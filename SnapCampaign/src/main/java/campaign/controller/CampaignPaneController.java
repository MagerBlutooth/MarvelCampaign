package campaign.controller;

import javafx.scene.Scene;
import campaign.view.pane.CampaignPane;
import campaign.view.pane.CampaignStage;

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
