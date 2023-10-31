package snapMain.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import snapMain.view.button.ButtonToolBar;

public abstract class ButtonToolBarPaneController<C extends MainDatabase> extends BasePaneController<C> {

    @FXML
    public ButtonToolBar buttonToolBar;

    public abstract void initializeButtonToolBar();

    @Override
    public void initialize(C controllerDatabase)
    {
        super.initialize(controllerDatabase);
        initializeButtonToolBar();
    }
    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }
}
