package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.node.WorldClearSelectNode;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.node.GridDisplayNode;

public class WorldClearSelectNodeController extends AdvPaneController  {

    @FXML
    WorldClearSelectNode worldClearNode;
    @FXML
    GridDisplayNode<Card> reclaimedCards;
    AdventureControlPane controlPane;
    @FXML
    ButtonToolBar buttonToolBar;
    @FXML

    Adventure adventure;
    AdventureControlPane adventureControlPane;

    public void initialize(AdvMainDatabase database, Adventure a, AdventureControlPane aPane)
    {
        adventure = a;
        adventureControlPane = aPane;
        BaseGridActionController<Card> gridActionController = new BaseGridActionController<>();
        gridActionController.initialize(database);
        reclaimedCards.initialize(a.reclaimCards(), TargetType.CARD, gridActionController, ViewSize.TINY, false);
    }

    @FXML
    public void draftCard()
    {
        adventureControlPane.draftCard();
    }

    @FXML
    public void healCard()
    {
        adventureControlPane.healCard();
    }

    @FXML
    public void clearWorld()
    {
        adventure.completeCurrentWorld();
        changeScene(adventureControlPane);
    }

    @Override
    public Scene getCurrentScene() {
        return worldClearNode.getScene();
    }

    @Override
    public void initializeButtonToolBar() {

    }
}
