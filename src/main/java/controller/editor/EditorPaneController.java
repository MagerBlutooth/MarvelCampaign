package controller.editor;

import controller.CampaignBasePaneController;
import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import model.thing.Thing;
import view.pane.editor.EditorPane;


public abstract class EditorPaneController extends CampaignBasePaneController {

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
