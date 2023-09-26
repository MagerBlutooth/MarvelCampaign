package adventure.controller;

import adventure.model.AdvControllerDatabase;
import campaign.controller.BasePaneController;
import campaign.controller.ControllerDatabase;
import campaign.view.pane.BasicPane;
import campaign.view.pane.BasicStage;
import javafx.scene.Scene;

public abstract class AdvPaneController extends BasePaneController {
    protected AdvControllerDatabase controllerDatabase;
    public abstract Scene getCurrentScene();

    public void changeScene(BasicPane cPane)
    {
        BasicStage primaryWindow = (BasicStage) getCurrentScene().getWindow();
        primaryWindow.initialize(cPane);
        primaryWindow.centerOnScreen();
    }

    public void initialize(AdvControllerDatabase controllerDatabase)
    {
        this.controllerDatabase = controllerDatabase;
    }

    public AdvControllerDatabase getDatabase() {
        return controllerDatabase;
    }
}
