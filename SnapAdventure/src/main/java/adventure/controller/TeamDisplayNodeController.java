package adventure.controller;

import adventure.model.Team;
import campaign.controller.MainDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import campaign.view.node.GridDisplayNode;
import javafx.fxml.FXML;

public class TeamDisplayNodeController {

    @FXML
    GridDisplayNode<Card> cardDisplay;
    @FXML
    public GridDisplayNode<Card> tempCardDisplay;

    Team team;

    MainDatabase database;

    public void initialize(MainDatabase d, Team t)
    {
        database = d;
        team = t;
        TeamGridActionController cardController = new TeamGridActionController();
        cardController.initialize(d, this);
        cardDisplay.initialize(t.getActiveCards(), ThingType.CARD, cardController, ViewSize.SMALL, false);
        tempCardDisplay.initialize(t.getTempCards(), ThingType.CARD, cardController, ViewSize.SMALL, false);
    }

    public void showCaptured()
    {

    }

    public void showMIA()
    {

    }

    public void showEliminated()
    {

    }


    public void refresh()
    {
        cardDisplay.refreshToMatch(team.getActiveCards());
        tempCardDisplay.refreshToMatch(team.getTempCards());
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
}
