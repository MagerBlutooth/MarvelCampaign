package campaign.controller;

import javafx.scene.Scene;
import campaign.view.pane.BasicPane;
import campaign.view.pane.BasicStage;

public abstract class BasePaneController<C extends ControllerDatabase> {

    protected C controllerDatabase;
    public abstract Scene getCurrentScene();

    public void changeScene(BasicPane cPane)
    {
        BasicStage primaryWindow = (BasicStage) getCurrentScene().getWindow();
        primaryWindow.initialize(cPane);
        primaryWindow.centerOnScreen();
    }

    public void initialize(C controllerDatabase)
    {
        this.controllerDatabase = controllerDatabase;
    }

    public ControllerDatabase getDatabase() {
        return controllerDatabase;
    }
}
