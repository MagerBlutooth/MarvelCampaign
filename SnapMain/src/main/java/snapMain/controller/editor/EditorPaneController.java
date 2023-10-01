package snapMain.controller.editor;

import snapMain.controller.ButtonToolBarPaneController;
import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import snapMain.model.thing.BaseObject;
import snapMain.view.pane.editor.EditorPane;


public abstract class EditorPaneController extends ButtonToolBarPaneController {

    @FXML
    Rectangle imageView;

    @FXML
    EditorPane editorPane;

    BaseObject baseObject;

    public void initialize(MainDatabase database, BaseObject t)
    {
        super.initialize(database);
        baseObject = t;
    }
}
