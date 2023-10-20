package adventure.model;

import adventure.model.target.*;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.StatusEffect;
import snapMain.model.target.TargetList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class Team {

    ActiveCardList teamCards;
    ActiveCardList tempCards;
    ActiveCardList freeAgentCards;
    ActiveCardList capturedCards;
    ActiveCardList miaCards;
    ActiveCardList eliminatedCards;
    List<InfinityStone> infinityStones;

    public Team()
    {
        teamCards = new ActiveCardList(new ArrayList<>());
        tempCards = new ActiveCardList(new ArrayList<>());
        freeAgentCards = new ActiveCardList(new ArrayList<>());
        capturedCards = new ActiveCardList(new ArrayList<>());
        miaCards = new ActiveCardList(new ArrayList<>());
        eliminatedCards = new ActiveCardList(new ArrayList<>());
        infinityStones = new ArrayList<>();
    }
    public Team(AdventureDatabase database, int numTeamMembers, int numCaptains)
    {
        this();
        ActiveCardList allCards = database.generateActiveCards();
        allCards.shuffle();
        for(int i = 0; i < numTeamMembers; i++)
        {
            teamCards.add(allCards.get(i));
        }
        allCards.removeAll(teamCards.getThings());
        freeAgentCards.addAll(allCards.getThings());
        for(int i = 0; i < numCaptains; i++)
        {
            teamCards.get(i).setStatus(StatusEffect.CAPTAIN, true);
        }
    }

    public String toSaveString() {
        String teamString =  teamCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                tempCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                freeAgentCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                capturedCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                miaCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                eliminatedCards.toSaveString();
        return Base64.getEncoder().encodeToString(teamString.getBytes());
    }

    public void convertFromString(String teamString, AdventureDatabase ad) {
        byte[] decodedBytes = Base64.getDecoder().decode(teamString);
        String decodedString = new String(decodedBytes);
        String[] teamList = decodedString.split(SnapMainConstants.CATEGORY_SEPARATOR);
        teamCards.fromSaveString(teamList[0], ad.getCards());
        tempCards.fromSaveString(teamList[1], ad.getCards());
        freeAgentCards.fromSaveString(teamList[2], ad.getCards());
        capturedCards.fromSaveString(teamList[3], ad.getCards());
        miaCards.fromSaveString(teamList[4], ad.getCards());
        eliminatedCards.fromSaveString(teamList[5], ad.getCards());
    }

    public ActiveCardList getTeamCards() {
        return teamCards;
    }

    public void captureCard(ActiveCard card) {
        if(teamCards.contains(card)) {
            capturedCards.add(card);
            teamCards.remove(card);
        }
        if(tempCards.contains(card))
        {
            makeCardFreeAgent(card);
            tempCards.remove(card);
        }
    }

    public void freeCapturedCard(ActiveCard card)
    {
        if(capturedCards.contains(card))
        {
            teamCards.add(card);
            capturedCards.remove(card);
        }
    }

    public void makeCardFreeAgent(ActiveCard card)
    {
        if(teamCards.contains(card) || tempCards.contains(card)) {
            freeAgentCards.add(card);
            teamCards.remove(card);
            tempCards.remove(card);
        }
    }


    public void eliminateCard(ActiveCard card) {

        if(teamCards.contains(card) || tempCards.contains(card)) {
            eliminatedCards.add(card);
            teamCards.remove(card);
            tempCards.remove(card);
        }
    }

    public void reviveCard(ActiveCard card) {
        if(eliminatedCards.contains(card))
        {
            teamCards.add(card);
            eliminatedCards.remove(card);
        }
    }

    public boolean returnCard(ActiveCard card) {
        if(miaCards.contains(card) || capturedCards.contains(card) || eliminatedCards.contains(card)) {
            teamCards.add(card);
            miaCards.remove(card);
            capturedCards.remove(card);
            eliminatedCards.remove(card);
            return true;
        }
        return false;
    }


    public void sendAway(ActiveCard card) {
        if(teamCards.contains(card)) {
            miaCards.add(card);
            teamCards.remove(card);
        }
    }

    public TargetList<ActiveCard> getCaptains() {
        ActiveCardList captains = new ActiveCardList(new ArrayList<>());
        for(ActiveCard c: getTeamCards())
        {
            if(c.hasStatus(StatusEffect.CAPTAIN))
                captains.add(c);
        }
        return captains;
    }

    public ActiveCardList getTempCards() {
        return tempCards;
    }

    public ActiveCardList getEliminatedCards() {
        return eliminatedCards;
    }

    public ActiveCardList getCapturedCards() {
        return capturedCards;
    }

    public ActiveCardList getMIACards()
    {
        return miaCards;
    }

    public ActiveCardList getFreeAgents()
    {
        return freeAgentCards;
    }

    public void healCard(ActiveCard c) {
        ActiveCard card = teamCards.get(c);
        if(card!=null) {
            card.setStatus(StatusEffect.WOUND, false);
        }
    }

    public ActiveCardList getWoundedCards() {
        ActiveCardList cards = new ActiveCardList(new ArrayList<>());
        for(ActiveCard c: teamCards)
        {
            if(c.hasStatus(StatusEffect.WOUND))
                cards.add(c);
        }
        return cards;
    }

    public void stationCard(Section s, ActiveCard c)
    {
        if(s.getStationedCards().size() < AdventureConstants.MAX_STATIONS) {
            s.stationCard(c);
            teamCards.remove(c);
        }
    }

    public boolean hasInfinityStone(InfinityStoneID id)
    {
        for(InfinityStone stone: infinityStones)
        {
            if(stone.getID() == id.getID())
                return true;
        }
        return false;
    }

    public void addCardToTeam(ActiveCard c) {
        teamCards.add(c);
    }

    public void gainInfinityStone(InfinityStone p) {
        infinityStones.add(p);
    }

    public void retrieveCapturedCards() {
        teamCards.addAll(capturedCards.getThings());
        capturedCards.clear();
    }

    public void loseTempCards() {
        tempCards.clear();
    }

    public void addCardToFreeAgents(ActiveCard card) {
        freeAgentCards.add(card);
    }

    public void removeFromFreeAgents(Card card) {
        ActiveCard aCard = null;
        for(ActiveCard c: freeAgentCards)
        {
            if(c.getID() == card.getID())
            {
                aCard = c;
                break;
            }
        }
        if(aCard != null)
           freeAgentCards.remove(aCard);
    }

    public void fromTempToTeam(ActiveCard c) {
        boolean removed = tempCards.remove(c);
        if(removed)
            teamCards.add(c);
    }

    public void fromTeamToTemp(ActiveCard c)
    {
        boolean removed = teamCards.remove(c);
        if(removed)
            tempCards.add(c);
    }

    public ActiveCardList getAllCards() {
        ActiveCardList allCards = new ActiveCardList(new ArrayList<>());
        allCards.addAll(teamCards.getThings());
        allCards.addAll(tempCards.getThings());
        allCards.addAll(freeAgentCards.getThings());
        allCards.addAll(eliminatedCards.getThings());
        allCards.addAll(capturedCards.getThings());
        allCards.addAll(miaCards.getThings());
        return allCards;
    }
}
