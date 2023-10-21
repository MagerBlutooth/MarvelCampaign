package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.target.ActiveCard;
import adventure.view.node.WorldClearSelectNode;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.node.control.ControlNode;

public class AdventureClearPaneController extends AdvPaneController implements GridActionController<ActiveCard> {

    @FXML
    Label matchCount;

    public void initialize(AdvMainDatabase database, Adventure a)
    {
        mainDatabase = database;
        matchCount.setText(a.getWorldMatchCount()+"");
    }

    @Override
    public Scene getCurrentScene() {
        return matchCount.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
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
