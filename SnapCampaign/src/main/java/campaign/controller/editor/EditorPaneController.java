package campaign.controller.editor;

import campaign.controller.ButtonToolBarPaneController;
import campaign.controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import campaign.model.thing.Thing;
import campaign.view.pane.editor.EditorPane;


public abstract class EditorPaneController extends ButtonToolBarPaneController {

    @FXML
    Rectangle imageView;

    @FXML
    EditorPane editorPane;

    Thing thing;

    public void initialize(ControllerDatabase database, Thing t)
    {
        super.initialize(database);
        thing = t;
    }
}