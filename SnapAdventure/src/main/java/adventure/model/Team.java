package adventure.model;

import adventure.model.thing.InfinityStone;
import adventure.model.thing.InfinityStoneID;
import adventure.model.thing.Section;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class Team {

    CardList teamCards;
    CardList tempCards;
    CardList freeAgentCards;
    CardList capturedCards;
    CardList miaCards;
    CardList eliminatedCards;
    List<InfinityStone> infinityStones;

    public Team()
    {
        teamCards = new CardList(new ArrayList<>());
        tempCards = new CardList(new ArrayList<>());
        freeAgentCards = new CardList(new ArrayList<>());
        capturedCards = new CardList(new ArrayList<>());
        miaCards = new CardList(new ArrayList<>());
        eliminatedCards = new CardList(new ArrayList<>());
        infinityStones = new ArrayList<>();
    }
    public Team(AdventureDatabase database, int numTeamMembers, int numCaptains)
    {
        this();
        List<Card> cards = new ArrayList<>(database.getCards());
        Collections.shuffle(cards);
        for(int i = 0; i < numTeamMembers; i++)
        {
            teamCards.add(cards.get(i));
        }
        cards.removeAll(teamCards.getCards());
        freeAgentCards.addAll(cards);
        for(int i = 0; i < numCaptains; i++)
        {
            teamCards.get(i).setCaptain(true);
        }
    }

    public String convertToString() {
        String teamString =  teamCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                tempCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                freeAgentCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                capturedCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                miaCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                eliminatedCards.toSaveString();
        return Base64.getEncoder().encodeToString(teamString.getBytes());
    }

    public void convertFromString(String teamString, TargetDatabase<Card> db) {
        byte[] decodedBytes = Base64.getDecoder().decode(teamString);
        String decodedString = new String(decodedBytes);
        String[] teamList = decodedString.split(SnapMainConstants.CATEGORY_SEPARATOR);
        teamCards.fromSaveString(teamList[0], db);
        tempCards.fromSaveString(teamList[1], db);
        freeAgentCards.fromSaveString(teamList[2], db);
        capturedCards.fromSaveString(teamList[3], db);
        miaCards.fromSaveString(teamList[4], db);
        eliminatedCards.fromSaveString(teamList[5], db);
    }

    public CardList getTeamCards() {
        return teamCards;
    }

    public void captureCard(Card card) {
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

    public void freeCapturedCard(Card card)
    {
        if(capturedCards.contains(card))
        {
            teamCards.add(card);
            capturedCards.remove(card);
        }
    }

    public void makeCardFreeAgent(Card card)
    {
        if(teamCards.contains(card) || tempCards.contains(card)) {
            freeAgentCards.add(card);
            teamCards.remove(card);
            tempCards.remove(card);
        }
    }


    public void eliminateCard(Card card) {

        if(teamCards.contains(card) || tempCards.contains(card)) {
            eliminatedCards.add(card);
            teamCards.remove(card);
            tempCards.remove(card);
        }
    }

    public void reviveCard(Card card) {
        if(eliminatedCards.contains(card))
        {
            teamCards.add(card);
            eliminatedCards.remove(card);
        }
    }

    public void returnCard(Card card) {
        if(miaCards.contains(card)) {
            teamCards.add(card);
            miaCards.remove(card);
        }
    }


    public void sendAway(Card card) {
        if(teamCards.contains(card)) {
            miaCards.add(card);
            teamCards.remove(card);
        }
    }

    public TargetList<Card> getCaptains() {
        CardList captains = new CardList(new ArrayList<>());
        for(Card c: getTeamCards())
        {
            if(c.isCaptain())
                captains.add(c);
        }
        return captains;
    }

    public TargetList<Card> getTempCards() {
        return tempCards;
    }

    public CardList getEliminatedCards() {
        return eliminatedCards;
    }

    public CardList getCapturedCards() {
        return capturedCards;
    }

    public CardList getMIACards()
    {
        return miaCards;
    }

    public CardList getFreeAgents()
    {
        return freeAgentCards;
    }

    public void healCard(Card c) {
        Card card = teamCards.get(c);
        if(card!=null) {
            card.setWounded(false);
        }
    }

    public TargetList<Card> getWoundedCards() {
        CardList cards = new CardList(new ArrayList<>());
        for(Card c: teamCards)
        {
            if(c.isWounded())
                cards.add(c);
        }
        return cards;
    }

    public void stationCard(Section s ,Card c)
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

    public void addCardToTeam(Card c) {
        teamCards.add(c);
    }

    public void gainInfinityStone(InfinityStone p) {
        infinityStones.add(p);
    }

    public Card getRandomCard() {
        if(!teamCards.isEmpty()) {
            List<Card> allCards = new ArrayList<>(teamCards.getThings());
            Collections.shuffle(allCards);
            return allCards.get(0);
        }
        return null;
    }

    public void retrieveCapturedCards() {
        teamCards.addAll(capturedCards.getCards());
        capturedCards.clear();
    }

    public void loseTempCards() {
        tempCards.clear();
    }

    public void addCardToFreeAgents(Card card) {
        freeAgentCards.add(card);
    }

    public void removeFromFreeAgents(Card card) {
        freeAgentCards.remove(card);
    }
}
