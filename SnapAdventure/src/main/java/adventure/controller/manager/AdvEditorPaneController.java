package adventure.controller.manager;

import adventure.model.AdvMainDatabase;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import snapMain.controller.ButtonToolBarPaneController;
import snapMain.model.target.BaseObject;
import snapMain.view.pane.editor.EditorPane;


public abstract class AdvEditorPaneController extends ButtonToolBarPaneController<AdvMainDatabase> {

    @FXML
    Rectangle imageView;

    @FXML
    EditorPane editorPane;

    BaseObject baseObject;

    public void initialize(AdvMainDatabase database, BaseObject t)
    {
        super.initialize(database);
        baseObject = t;
    }
}
