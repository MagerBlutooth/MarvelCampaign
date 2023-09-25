package adventure.controller;

import campaign.controller.ControllerDatabase;
import campaign.view.pane.CampaignPane;
import campaign.view.pane.CampaignStage;
import javafx.scene.Scene;

public abstract class JourneyPaneController {

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
