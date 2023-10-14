package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.Team;
import adventure.model.adventure.Adventure;
import adventure.view.pane.AdvStartPane;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import snapMain.controller.grid.GridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class AdvNewProfilePaneController extends AdvPaneController implements GridActionController<Card> {

    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    TextField profileField;
    @FXML
    GridDisplayNode<Card> teamIntro;
    @FXML
    GridDisplayNode<Card> captainIntro;
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
        teamIntro.initialize(t.getTeamCards(), TargetType.CARD, this, ViewSize.SMALL, false);
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
    public ControlNode<Card> createControlNode(Card card, IconImage i, ViewSize v, boolean blind) {
       ControlNode<Card> cardControlNode = new ControlNode<>();
       cardControlNode.initialize(mainDatabase, card, i, v, true);
       return cardControlNode;
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
