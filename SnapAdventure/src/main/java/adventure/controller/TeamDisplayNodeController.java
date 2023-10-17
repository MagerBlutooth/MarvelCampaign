package adventure.controller;

import adventure.model.Team;
import adventure.model.adventure.Adventure;
import adventure.view.node.InfinityStoneDisplayNode;
import adventure.view.pane.AdventureControlPane;
import adventure.view.popup.CardDisplayPopup;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import snapMain.controller.MainDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;

public class TeamDisplayNodeController {

    @FXML
    Button captureButton;
    @FXML
    Button miaButton;
    @FXML
    GridDisplayNode<Card> teamCardDisplay;
    @FXML
    InfinityStoneDisplayNode infinityStoneDisplay;
    @FXML
    public GridDisplayNode<Card> tempCardDisplay;
    @FXML
    public Button eliminateButton;
    @FXML
    Button stationedButton;
    TeamGridActionController cardController;
    TempGridActionController tempController;
    @FXML
    LostCardGridActionController lostCardController;

    Team team;
    Adventure adventure;

    MainDatabase database;
    AdventureControlPane adventureControlPane;

    public void initialize(MainDatabase d, Team t, AdventureControlPane aPane)
    {
        database = d;
        team = t;
        adventureControlPane = aPane;
        adventure = aPane.getAdventure();
        initControllers(d, t);
        infinityStoneDisplay.initialize(database, t);
    }

    private void initControllers(MainDatabase d, Team t) {
        cardController = new TeamGridActionController();
        tempController = new TempGridActionController();
        lostCardController = new LostCardGridActionController();
        cardController.initialize(d, this);
        tempController.initialize(d, this);
        teamCardDisplay.initialize(t.getTeamCards(), TargetType.CARD, cardController, ViewSize.SMALL, false);
        teamCardDisplay.setPrefColumns(6);
        tempCardDisplay.initialize(t.getTempCards(), TargetType.CARD, tempController, ViewSize.SMALL, false);
    }

    public void showCaptured()
    {
        CardDisplayPopup popup = new CardDisplayPopup(team.getCapturedCards(),
                captureButton.localToScreen(0.0,0.0));
        lostCardController.initialize(database, this, popup.getGridDisplayController());
        popup.initialize(lostCardController);
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                popup.hide();
            }
        });
    }

    public void showMIA()
    {
        CardDisplayPopup popup = new CardDisplayPopup(team.getMIACards(),
                miaButton.localToScreen(0.0,0.0));
        lostCardController.initialize(database, this, popup.getGridDisplayController());
        popup.initialize(lostCardController);
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                popup.hide();
            }
        });
    }

    public void showEliminated()
    {
        CardDisplayPopup popup = new CardDisplayPopup(team.getEliminatedCards(),
                eliminateButton.localToScreen(0.0,0.0));
        lostCardController.initialize(database, this, popup.getGridDisplayController());
        popup.initialize(lostCardController);
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                popup.hide();
            }
        });
    }

    public void showStationed()
    {
        CardDisplayPopup popup = new CardDisplayPopup(adventure.getStationedCards(),
                stationedButton.localToScreen(0.0,0.0));
        lostCardController.initialize(database, this, popup.getGridDisplayController());
        popup.initialize(lostCardController);
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                popup.hide();
            }
        });
    }


    public void refresh()
    {
        teamCardDisplay.refreshToMatch(team.getTeamCards());
        tempCardDisplay.refreshToMatch(team.getTempCards());
        infinityStoneDisplay.refresh();
    }

    public void capture(Card card)
    {
        team.captureCard(card);
        adventureControlPane.refreshToMatch();
    }

    public void eliminate(Card card) {
        team.eliminateCard(card);
        adventureControlPane.refreshToMatch();
    }

    public void revive(Card card)
    {
        team.reviveCard(card);
        adventureControlPane.refreshToMatch();
    }

    public void free(Card card)
    {
        team.freeCapturedCard(card);
        adventureControlPane.refreshToMatch();
    }

    public void sendAway(Card card)
    {
        team.sendAway(card);
        adventure.sendAway(card);
        adventureControlPane.refreshToMatch();
    }
    public void returnCard(Card card)
    {
        adventure.reclaimCard(card);
        adventureControlPane.refreshToMatch();
    }

    public void update(Card subject) {
        teamCardDisplay.update(subject);
        tempCardDisplay.update(subject);
    }

    public void toggleWound(Card card) {
        card.setWounded(!card.isWounded());
        update(card);
    }

    public void toggleCaptain(Card card) {
        card.setCaptain(!card.isCaptain());
        update(card);
    }

    public void makeCardFreeAgent(Card card) {
        team.makeCardFreeAgent(card);
        adventureControlPane.refreshToMatch();
    }

    public void focusTeamNode() {
        teamCardDisplay.setFocusTraversable(true);
    }

    public void fromTempToTeam(Card subject) {
        team.fromTempToTeam(subject);
        adventureControlPane.refreshToMatch();
    }

    public void teamToTemp(Card subject) {
        team.fromTeamToTemp(subject);
        adventureControlPane.refreshToMatch();
    }
}
