package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.Team;
import adventure.model.adventure.Adventure;
import adventure.model.target.ActiveCard;
import adventure.view.node.ActiveCardControlNode;
import adventure.view.pane.AdvStartPane;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class NewProfilePaneController extends FullViewPaneController implements GridActionController<ActiveCard> {

    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    TextField profileField;
    @FXML
    GridDisplayNode<ActiveCard> teamIntro;
    @FXML
    GridDisplayNode<ActiveCard> captainIntro;
    @FXML
    Button startButton;
    Adventure adventure;
    AdvStartPane backPane;

    public void initialize(AdvMainDatabase db, Adventure a, AdvStartPane pane)
    {
        mainDatabase = db;
        adventure = a;
        backPane = pane;
        startButton.setDisable(true);
        initializeButtonToolBar();
        Team t = adventure.getTeam();
        teamIntro.initialize(t.getTeamCards(), TargetType.CARD, this, ViewSize.SMALL, true);
        teamIntro.setFitToHeight(true);
        captainIntro.initialize(t.getCaptains(), TargetType.CARD, this, ViewSize.SMALL, false);
        initializeStartButton();
    }

    private void initializeStartButton() {
        profileField.textProperty().addListener((observableValue, s, t1) -> {
            if(t1.isBlank())
                startButton.setDisable(true);
            else
                startButton.setDisable(false);
        });
    }

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        buttonToolBar.initialize(backPane);
    }

    @Override
    public ControlNode<ActiveCard> createControlNode(ActiveCard card, IconImage i, ViewSize v, boolean statusVisible) {
       ActiveCardControlNode cardControlNode = new ActiveCardControlNode();
       cardControlNode.initialize(mainDatabase, card, i, v, statusVisible);
       return cardControlNode;
    }

    @Override
    public ControlNode<ActiveCard> createEmptyNode(ViewSize v) {
        ControlNode<ActiveCard> emptyNode = new ControlNode<>();
        emptyNode.initialize(mainDatabase.grabBlankImage(TargetType.CARD), v);
        return emptyNode;
    }

    @FXML
    public void startAdventure()
    {
        AdventureControlPane controlPane = new AdventureControlPane();
        adventure.setProfileName(profileField.getText().trim());
        controlPane.initialize(mainDatabase, adventure);
        changeScene(controlPane);
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
    public void setMouseEvents(ControlNode<ActiveCard> displayControlNode) {

    }
}
