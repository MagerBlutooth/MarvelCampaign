package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdvMasterThingDatabase;
import adventure.model.World;
import adventure.model.adventure.Adventure;
import adventure.view.node.WorldClearSelectNode;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class WorldClearPaneController extends AdvPaneController implements GridActionController<Card> {

    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    WorldClearSelectNode worldClearNode;
    @FXML
    GridDisplayNode<Card> reclaimedCards;
    @FXML
    GridDisplayNode<Card> eliminatedCards;
    @FXML
    Button healChoice;

    AdventureControlPane controlPane;

    public void initialize(AdvMainDatabase database, Adventure a, AdventureControlPane cPane)
    {
        World w= a.getCurrentWorld();
        reclaimedCards.initialize(a.getCapturedCards(), TargetType.CARD, this, ViewSize.TINY, false);
    }

    @Override
    public Scene getCurrentScene() {
        return null;
    }

    @Override
    public void initializeButtonToolBar() {
        buttonToolBar.initialize(controlPane);
    }


    @Override
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
        return null;
    }

    @Override
    public void saveGridNode(ControlNode<Card> node) {

    }

    @Override
    public void createTooltip(ControlNode<Card> n) {

    }

    @Override
    public void createContextMenu(ControlNode<Card> n) {

    }

    @Override
    public void setMouseEvents(ControlNode<Card> displayControlNode) {

    }
}
