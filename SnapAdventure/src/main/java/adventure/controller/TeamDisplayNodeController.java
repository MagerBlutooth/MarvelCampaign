package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.Team;
import adventure.model.adventure.Adventure;
import adventure.model.target.ActiveCard;
import adventure.view.node.InfinityStoneDisplayNode;
import adventure.view.pane.AdventureClearPane;
import adventure.view.pane.AdventureControlPane;
import adventure.view.popup.CardDisplayPopup;
import adventure.view.popup.IntegerPromptDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import snapMain.controller.MainDatabase;
import snapMain.model.target.StatusEffect;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.menu.FilterMenuButton;
import snapMain.view.menu.SortMenuButton;
import snapMain.view.node.GridDisplayNode;

import java.util.Optional;

public class TeamDisplayNodeController {

    @FXML
    Button captureButton;
    @FXML
    Button miaButton;
    @FXML
    GridDisplayNode<ActiveCard> teamCardDisplay;
    @FXML
    InfinityStoneDisplayNode infinityStoneDisplay;
    @FXML
    public GridDisplayNode<ActiveCard> tempCardDisplay;
    @FXML
    public Button eliminateButton;
    @FXML
    Button stationedButton;
    TeamGridActionController cardController;
    TempGridActionController tempController;
    @FXML
    LostCardGridActionController lostCardController;
    @FXML
    SortMenuButton<ActiveCard> sortMenuButton;
    @FXML
    FilterMenuButton<ActiveCard> filterMenuButton;

    Team team;
    Adventure adventure;

    AdvMainDatabase database;
    AdventureControlPane adventureControlPane;

    public void initialize(AdvMainDatabase d, Team t, AdventureControlPane aPane)
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
        teamCardDisplay.initialize(t.getTeamCards(), TargetType.CARD, cardController, ViewSize.SMALL, true);
        teamCardDisplay.setPrefColumns(6);
        tempCardDisplay.initialize(t.getTempCards(), TargetType.CARD, tempController, ViewSize.SMALL, true);
        sortMenuButton.initialize(teamCardDisplay.getController());
        filterMenuButton.initialize(teamCardDisplay.getController());
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

    public void defectAll()
    {
        team.tempCardsExpire();
        tempCardDisplay.refreshToMatch(team.getTempCards());
    }


    public void refresh()
    {
        teamCardDisplay.refreshToMatch(team.getTeamCards());
        tempCardDisplay.refreshToMatch(team.getTempCards());
        infinityStoneDisplay.refresh();
    }

    public void capture(ActiveCard card)
    {
        team.captureCard(card);
        adventureControlPane.refreshToMatch();
    }

    public void eliminate(ActiveCard card) {
        team.eliminateCard(card);
        adventureControlPane.refreshToMatch();
    }

    public void revive(ActiveCard card)
    {
        team.reviveCard(card);
        adventureControlPane.refreshToMatch();
    }

    public void free(ActiveCard card)
    {
        team.freeCapturedCard(card);
        adventureControlPane.refreshToMatch();
    }

    public void sendAway(ActiveCard card)
    {
        if(adventure.getCurrentWorldNum() < 8) {
            IntegerPromptDialog integerPromptDialog = new IntegerPromptDialog();
            integerPromptDialog.initialize("Send Away to Which World?",
                    adventure.getCurrentWorldNum() + 1,
                    AdventureConstants.NUMBER_OF_WORLDS);
            Optional<Integer> worldToSend = integerPromptDialog.showAndWait();
            worldToSend.ifPresent(w -> {
                team.sendAway(card);
                adventure.sendAway(w, card);
            });
            adventureControlPane.refreshToMatch();
        }
        else {
            adventure.sendAway(9, card);
        }
    }
    public void returnCard(ActiveCard card)
    {
        adventure.reclaimCard(card);
        adventureControlPane.refreshToMatch();
    }

    public void update(ActiveCard subject) {
        teamCardDisplay.update(subject);
        tempCardDisplay.update(subject);
    }

    public void toggleWound(ActiveCard card) {
        card.toggleStatus(StatusEffect.WOUND);
        update(card);
    }

    public void toggleCaptain(ActiveCard card) {
        card.toggleStatus(StatusEffect.CAPTAIN);
        update(card);
    }
    public void togglePig(ActiveCard card) {
        card.toggleStatus(StatusEffect.PIG);
        update(card);
    }

    public void toggleRaptor(ActiveCard card)
    {
        card.toggleStatus(StatusEffect.RAPTOR);
        update(card);
    }

    public void makeCardFreeAgent(ActiveCard card) {
        team.makeCardFreeAgent(card);
        adventureControlPane.refreshToMatch();
    }
    public void fromTempToTeam(ActiveCard subject) {
        team.fromTempToTeam(subject);
        adventureControlPane.refreshToMatch();
    }

    public void teamToTemp(ActiveCard subject) {
        team.fromTeamToTemp(subject);
        adventureControlPane.refreshToMatch();
    }
}
