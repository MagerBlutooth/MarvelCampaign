package adventure.model;

import adventure.model.target.*;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.logger.MLogger;
import snapMain.model.target.Card;
import snapMain.model.target.StatusEffect;
import snapMain.model.target.TargetList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

public class Team {

    ActiveCardList allCards;
    ActiveCardList teamCards;
    ActiveCardList tempCards;
    ActiveCardList freeAgentCards;
    ActiveCardList capturedCards;
    ActiveCardList miaCards;
    ActiveCardList eliminatedCards;
    InfinityStoneList infinityStones;

    MLogger logger = new MLogger(Team.class);

    public Team() {
        teamCards = new ActiveCardList();
        tempCards = new ActiveCardList();
        freeAgentCards = new ActiveCardList();
        capturedCards = new ActiveCardList();
        miaCards = new ActiveCardList();
        eliminatedCards = new ActiveCardList();
        infinityStones = new InfinityStoneList();
        allCards = new ActiveCardList();
    }

    public Team(AdventureDatabase database, int numTeamMembers, int numCaptains) {
        this();
        allCards = database.generateActiveCards();
        ActiveCardList allCardsCopy = new ActiveCardList();
        allCardsCopy = allCardsCopy.cloneNewList(allCards.getThings());
        allCardsCopy.shuffle();
        for (int i = 0; i < numTeamMembers; i++) {
            teamCards.add(allCardsCopy.get(i));
        }
        allCardsCopy.removeAll(teamCards.getThings());
        freeAgentCards.addAll(allCardsCopy.getThings());
        for (int i = 0; i < numCaptains; i++) {
            teamCards.get(i).setStatus(StatusEffect.CAPTAIN, true);
        }
    }

    public String toSaveString() {
        String teamString = teamCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                tempCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                freeAgentCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                capturedCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                miaCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR +
                eliminatedCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR
                + allCards.toSaveString() + SnapMainConstants.CATEGORY_SEPARATOR
                + infinityStones.toSaveString();
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
        allCards.fromSaveString(teamList[6], ad.getCards());
        infinityStones.fromSaveString(teamList[7], ad.getTokens());
    }

    public ActiveCardList getTeamCards() {
        return teamCards;
    }

    public void captureCard(ActiveCard card) {
        if (teamCards.contains(card)) {
            capturedCards.add(card);
            teamCards.remove(card);
            card.clearExhaustion();
            logger.info(card + " was captured!");
        }
        else if (tempCards.contains(card)) {
            makeCardFreeAgent(card);
            tempCards.remove(card);
            card.clearExhaustion();
            logger.info(card + " was captured!");
        }
    }

    public void freeCapturedCard(ActiveCard card) {
        if (capturedCards.contains(card)) {
            teamCards.add(card);
            capturedCards.remove(card);
            logger.info(card + " was freed!");
        }
    }

    public void makeCardFreeAgent(ActiveCard card) {
        if (teamCards.contains(card) || tempCards.contains(card)) {
            freeAgentCards.add(card);
            teamCards.remove(card);
            tempCards.remove(card);
            card.clearExhaustion();
            logger.info(card+ " defected to the enemy side!");
        }
    }

    public void eliminateCard(ActiveCard card) {
        if (teamCards.contains(card) || tempCards.contains(card)) {
            eliminatedCards.add(card);
            teamCards.remove(card);
            tempCards.remove(card);
            card.clearExhaustion();
            logger.info(card+ " was eliminated!");
        }
    }

    public void reviveCard(ActiveCard card) {
        if (eliminatedCards.contains(card)) {
            teamCards.add(card);
            eliminatedCards.remove(card);
            card.clearExhaustion();
            logger.info(card+ " was revived!");
        }
    }

    public boolean returnCard(ActiveCard card) {
        if (miaCards.contains(card) || capturedCards.contains(card) || eliminatedCards.contains(card)) {
            teamCards.add(card);
            miaCards.remove(card);
            capturedCards.remove(card);
            eliminatedCards.remove(card);
            logger.info(card+ " returned to team");
            return true;
        }
        return false;
    }


    public void sendAway(ActiveCard card) {
        if (teamCards.contains(card)) {
            miaCards.add(card);
            teamCards.remove(card);
            card.clearExhaustion();
            logger.info(card+ " was sent away!");
        }
    }

    public TargetList<ActiveCard> getCaptains() {
        ActiveCardList captains = new ActiveCardList(new ArrayList<>());
        for (ActiveCard c : getTeamCards()) {
            if (c.hasStatus(StatusEffect.CAPTAIN))
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

    public ActiveCardList getMIACards() {
        return miaCards;
    }

    public ActiveCardList getFreeAgents() {
        return freeAgentCards;
    }

    public void healCard(ActiveCard c) {
        ActiveCard card = teamCards.get(c);
        if (card != null) {
            card.setStatus(StatusEffect.WOUND, false);
            logger.info(card+ "'s wounds healed!");
        }
    }

    public ActiveCardList getWoundedCards() {
        ActiveCardList cards = new ActiveCardList(new ArrayList<>());
        for (ActiveCard c : teamCards) {
            if (c.hasStatus(StatusEffect.WOUND))
                cards.add(c);
        }
        return cards;
    }

    public void stationCard(Section s, ActiveCard c) {
        if (s.getStationedCards().size() < AdventureConstants.MAX_STATIONS) {
            s.stationCard(c);
            logger.info(c+ " was stationed in " + s);
            teamCards.remove(c);
            c.clearExhaustion();
        }
    }

    public void unstationCard(Section s, ActiveCard c) {
        s.unstationCard(c);
        teamCards.add(c);
        logger.info(c+ " was unstationed from " + s);
    }

    public boolean hasInfinityStone(InfinityStoneID id) {
        for (InfinityStone stone : infinityStones) {
            if (stone.getID() == id.getID())
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

    public ActiveCardList retrieveCapturedCards() {
        teamCards.addAll(capturedCards.getThings());
        ActiveCardList tempList = new ActiveCardList(capturedCards.getThings());
        capturedCards.clear();
        return tempList;
    }

    public void tempCardsExpire() {
        freeAgentCards.addAll(tempCards.getThings());
        tempCards.clear();
    }

    public void addCardToFreeAgents(ActiveCard card) {
        freeAgentCards.add(card);
    }

    public void removeFromFreeAgents(Card card) {
        ActiveCard aCard = null;
        for (ActiveCard c : freeAgentCards) {
            if (c.getID() == card.getID()) {
                aCard = c;
                break;
            }
        }
        if (aCard != null)
            freeAgentCards.remove(aCard);
    }

    public void fromTempToTeam(ActiveCard c) {
        boolean removed = tempCards.remove(c);
        if (removed) {
            c.setTemp(false);
            teamCards.add(c);
        }
    }

    public void fromTeamToTemp(ActiveCard c) {
        boolean removed = teamCards.remove(c);
        if (removed) {
            c.setTemp(true);
            tempCards.add(c);
            logger.info(c+ " was moved to temp.");
        }
    }

    public ActiveCardList getAllCards() {
        return allCards;
    }

    public ActiveCardList retrieveMIACards(MIACardTracker miaCardTracker, int world) {
        ActiveCardList retrievedCards = miaCardTracker.lookup(world);
        teamCards.addAll(miaCards.getThings());
        miaCards.removeAll(miaCards.getThings());
        miaCardTracker.removeCards(world, retrievedCards);
        return retrievedCards;
    }

    public void sendCapturedCardsAway(MIACardTracker miaCardTracker, int world) {
        for (ActiveCard c : capturedCards) {
            miaCards.add(c);
            miaCardTracker.addCard(world, c);
        }
        capturedCards.clear();
    }

    public void exhaustedCardsRecover() {
        for (ActiveCard c : teamCards) {
            c.setStatus(StatusEffect.EXHAUSTED, false);
            c.setStatus(StatusEffect.RECOVERING, false);
        }
    }

    public void loseInfinityStone(InfinityStone randomInfinityStone) {
        infinityStones.remove(randomInfinityStone);
    }

    public InfinityStone getRandomInfinityStone() {
        Random random = new Random();
        int i = random.nextInt(infinityStones.size());
        return infinityStones.get(i);
    }

    public void woundCard(ActiveCard c) {
        ActiveCard woundedCard;
        woundedCard = getTeamCards().lookupActiveCard(c.getID());
        if(!woundedCard.isActualThing())
            woundedCard = getTempCards().lookupActiveCard(c.getID());
        woundedCard.setStatus(StatusEffect.WOUND, true);
    }

    public ActiveCardList getMissingCards(AdvMainDatabase database) {
        ActiveCardList missingCards = new ActiveCardList();
        for(Card c: database.getCards())
        {
            if(!getAllCards().contains(c.getID()))
                missingCards.add(new ActiveCard(c));
        }
        return  missingCards;
    }

    public boolean hasAllInfinityStones() {
        return infinityStones.size() == AdventureConstants.INFINITY_STONE_COUNT;
    }
}
