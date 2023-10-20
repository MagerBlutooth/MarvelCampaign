package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdvMasterThingDatabase;
import adventure.model.World;
import adventure.model.adventure.Adventure;
import adventure.model.target.ActiveCard;
import adventure.view.node.WorldClearSelectNode;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class WorldClearPaneController extends AdvPaneController implements GridActionController<ActiveCard> {

    @FXML
    Label matchCount;
    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    WorldClearSelectNode worldClearNode;

    AdventureControlPane controlPane;

    public void initialize(AdvMainDatabase database, Adventure a, AdventureControlPane cPane)
    {
        mainDatabase = database;
        worldClearNode.initialize(database, a, cPane);
        matchCount.setText(a.getWorldMatchCount()+"");
    }

    @Override
    public Scene getCurrentScene() {
        return worldClearNode.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        buttonToolBar.initialize(controlPane);
    }


    @Override
    public ControlNode<ActiveCard> createControlNode(ActiveCard card, IconImage i, ViewSize v, boolean blind) {
        ControlNode<ActiveCard> controlNode = new ControlNode<>();
        controlNode.initialize(mainDatabase, card, i, v, blind);
        return controlNode;
    }

    @Override
    public void saveGridNode(ControlNode<ActiveCard> node) {

    }

    @Override
    public void createTooltip(ControlNode<ActiveCard> n) {

    }

    @Override
    public void createContextMenu(ControlNode<ActiveCard> n) {

    }

    @Override
    public ControlNode<ActiveCard> createEmptyNode(ViewSize v)
    {
        ControlNode<ActiveCard> cardNode = new ControlNode<>();
        cardNode.initialize(mainDatabase, new ActiveCard(), mainDatabase.grabBlankImage(TargetType.CARD),
                v,false);
        return cardNode;
    }

    @Override
    public void setMouseEvents(ControlNode<ActiveCard> displayControlNode) {

    }
}
