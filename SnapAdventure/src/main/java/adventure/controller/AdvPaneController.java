package adventure.controller;

import adventure.model.AdvMainDatabase;
import campaign.controller.BasePaneController;
import campaign.view.pane.BasicPane;
import campaign.view.pane.BasicStage;
import javafx.scene.Scene;

public abstract class AdvPaneController extends BasePaneController {
    protected AdvMainDatabase mainDatabase;
    public abstract Scene getCurrentScene();

    public void changeScene(BasicPane cPane)
    {
        BasicStage primaryWindow = (BasicStage) getCurrentScene().getWindow();
        primaryWindow.initialize(cPane);
        primaryWindow.centerOnScreen();
    }

    public void initialize(AdvMainDatabase controllerDatabase)
    {
        this.mainDatabase = controllerDatabase;
    }

    public abstract void initializeButtonToolBar();

    public AdvMainDatabase getDatabase() {
        return mainDatabase;
    }
}
