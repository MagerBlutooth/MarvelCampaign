package snapMain.controller;

import javafx.scene.Scene;
import snapMain.view.pane.FullViewPane;
import snapMain.view.pane.BasicStage;

public abstract class BasePaneController<C extends MainDatabase> {

    protected C mainDatabase;
    public abstract Scene getCurrentScene();

    public void changeScene(FullViewPane cPane)
    {
        BasicStage primaryWindow = (BasicStage) getCurrentScene().getWindow();
        primaryWindow.initialize(cPane);
        primaryWindow.centerOnScreen();
    }

    public void initialize(C controllerDatabase)
    {
        this.mainDatabase = controllerDatabase;
    }

    public MainDatabase getDatabase() {
        return mainDatabase;
    }
}
