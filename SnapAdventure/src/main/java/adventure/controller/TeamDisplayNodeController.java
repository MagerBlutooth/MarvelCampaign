package adventure.controller;

import adventure.model.Team;
import adventure.model.adventure.Adventure;
import adventure.view.node.InfinityStoneDisplayNode;
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
    InfinityStoneDisplayNode infinityStoneDisplay;
    @FXML
    GridDisplayNode<Card> cardDisplay;
    @FXML
    public GridDisplayNode<Card> tempCardDisplay;
    @FXML
    public Button eliminateButton;
    @FXML
    Button stationedButton;

    TeamGridActionController cardController;

    Team team;
    Adventure adventure;

    MainDatabase database;

    public void initialize(MainDatabase d, Team t, Adventure a)
    {
        database = d;
        team = t;
        adventure = a;
        cardController = new TeamGridActionController();
        cardController.initialize(d, this);
        cardDisplay.initialize(t.getActiveCards(), TargetType.CARD, cardController, ViewSize.SMALL, false);
        tempCardDisplay.initialize(t.getTempCards(), TargetType.CARD, cardController, ViewSize.SMALL, false);
        infinityStoneDisplay.initialize(database, t);
    }

    public void showCaptured()
    {
        CardDisplayPopup popup = new CardDisplayPopup(team.getCapturedCards(), eliminateButton.localToScene(0.0,0.0), cardController);
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                popup.hide();
            }
        });
    }

    public void showMIA()
    {
        CardDisplayPopup popup = new CardDisplayPopup(team.getMIACards(), eliminateButton.localToScene(0.0,0.0), cardController);
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                popup.hide();
            }
        });
    }

    public void showEliminated()
    {
        CardDisplayPopup popup = new CardDisplayPopup(team.getEliminatedCards(), eliminateButton.localToScene(0.0,0.0), cardController);
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                popup.hide();
            }
        });
    }

    public void showStationed()
    {
        CardDisplayPopup popup = new CardDisplayPopup(adventure.getStationedCards(), stationedButton.localToScene(0.0,0.0), cardController);
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                popup.hide();
            }
        });
    }


    public void refresh()
    {
        cardDisplay.refreshToMatch(team.getActiveCards());
        tempCardDisplay.refreshToMatch(team.getTempCards());
        infinityStoneDisplay.refresh();
    }

    public void capture(Card card)
    {
        team.captureCard(card);
        refresh();
    }

    public void eliminate(Card card) {
        team.eliminateCard(card);
        refresh();
    }

    public void revive(Card card)
    {
        team.reviveCard(card);
        refresh();
    }

    public void free(Card card)
    {
        team.freeCapturedCard(card);
        refresh();
    }

    public void sendAway(Card card)
    {
        team.sendAway(card);
        adventure.sendAway(card);
        refresh();
    }
    public void returnCard(Card card)
    {
        team.returnCard(card);
        refresh();
    }

    public void update(Card subject) {
        cardDisplay.update(subject);
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
        refresh();
    }
}
