package adventure.controller.manager;

import adventure.model.AdvControllerDatabase;
import campaign.controller.ButtonToolBarPaneController;
import campaign.controller.ControllerDatabase;
import campaign.model.thing.Thing;
import campaign.view.pane.editor.EditorPane;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;


public abstract class AdvEditorPaneController extends ButtonToolBarPaneController<AdvControllerDatabase> {

    @FXML
    Rectangle imageView;

    @FXML
    EditorPane editorPane;

    Thing thing;

    public void initialize(AdvControllerDatabase database, Thing t)
    {
        super.initialize(database);
        thing = t;
    }
}
