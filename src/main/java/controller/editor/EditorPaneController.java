package controller.editor;

import controller.CampaignBasePaneController;
import controller.CampaignPaneController;
import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import model.thing.Thing;
import view.button.ButtonToolBar;
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
