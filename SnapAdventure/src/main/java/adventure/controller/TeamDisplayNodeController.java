package adventure.controller;

import adventure.model.Team;
import campaign.controller.ControllerDatabase;
import campaign.controller.grid.BaseGridActionController;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import campaign.view.node.GridDisplayNode;
import javafx.fxml.FXML;

public class TeamDisplayNodeController {

    @FXML
    GridDisplayNode<Card> cardDisplay;
    Team team;

    ControllerDatabase database;

    public void initialize(ControllerDatabase d, Team t)
    {
        database = d;
        team = t;

        BaseGridActionController<Card> cardController = new BaseGridActionController<>();
        cardController.initialize(d);
        cardDisplay.initialize(t.getActiveCards(), ThingType.CARD, cardController, ViewSize.SMALL, false);
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
        team.freeCard(card);
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
}
