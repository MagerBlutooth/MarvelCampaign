package adventure.controller;

import adventure.model.AdvMainDatabase;
import javafx.scene.Scene;
import snapMain.controller.BasePaneController;
import snapMain.view.pane.BasicStage;
import snapMain.view.pane.FullViewPane;

public abstract class FullViewPaneController extends BasePaneController<AdvMainDatabase> {
    protected AdvMainDatabase mainDatabase;
    public abstract Scene getCurrentScene();

    public void changeScene(FullViewPane cPane)
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
