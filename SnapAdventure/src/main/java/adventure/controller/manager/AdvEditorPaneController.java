package adventure.controller.manager;

import adventure.model.AdvMainDatabase;
import campaign.controller.ButtonToolBarPaneController;
import campaign.model.thing.Thing;
import campaign.view.pane.editor.EditorPane;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;


public abstract class AdvEditorPaneController extends ButtonToolBarPaneController<AdvMainDatabase> {

    @FXML
    Rectangle imageView;

    @FXML
    EditorPane editorPane;

    Thing thing;

    public void initialize(AdvMainDatabase database, Thing t)
    {
        super.initialize(database);
        thing = t;
    }
}
