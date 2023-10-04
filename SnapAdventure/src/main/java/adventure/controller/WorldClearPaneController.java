package adventure.controller;

import adventure.model.AdvMasterThingDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.node.WorldClearSelectNode;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import snapMain.model.target.Card;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.node.GridDisplayNode;

public class WorldClearPaneController extends AdvPaneController {

    @FXML
    WorldClearSelectNode worldClearNode;
    @FXML
    GridDisplayNode<Card> reclaimedCards;
    @FXML
    ButtonToolBar buttonToolBar;

    AdventureControlPane controlPane;

    public void initialize(AdvMasterThingDatabase database, Adventure a,AdventureControlPane cPane)
    {

    }

    @Override
    public Scene getCurrentScene() {
        return null;
    }

    @Override
    public void initializeButtonToolBar() {
        buttonToolBar.initialize(controlPane);
    }
}
