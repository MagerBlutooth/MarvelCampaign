package adventure.model;

import campaign.model.constants.CampaignConstants;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.CardList;
import campaign.model.thing.ThingList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class Team {

    CardList activeCards;
    CardList tempCards;
    CardList freeAgentCards;
    CardList capturedCards;
    CardList miaCards;
    CardList eliminatedCards;

    public Team()
    {
        activeCards = new CardList(new ArrayList<>());
        tempCards = new CardList(new ArrayList<>());
        freeAgentCards = new CardList(new ArrayList<>());
        capturedCards = new CardList(new ArrayList<>());
        miaCards = new CardList(new ArrayList<>());
        eliminatedCards = new CardList(new ArrayList<>());
    }
    public Team(AdventureDatabase database)
    {
        this();
        List<Card> cards = new ArrayList<>(database.getCards());
        Collections.shuffle(cards);
        for(int i = 0; i < AdventureConstants.STARTING_CARDS; i++)
        {
            activeCards.add(cards.get(i));
        }
        cards.removeAll(activeCards.getCards());
        freeAgentCards.addAll(cards);
        for(int i = 0; i < AdventureConstants.STARTING_CAPTAINS; i++)
        {
            activeCards.get(i).setCaptain(true);
        }
    }

    public String convertToString() {
        String teamString =  activeCards.toSaveString() + CampaignConstants.CATEGORY_SEPARATOR +
                tempCards.toSaveString() + CampaignConstants.CATEGORY_SEPARATOR +
                freeAgentCards.toSaveString() + CampaignConstants.CATEGORY_SEPARATOR +
                capturedCards.toSaveString() + CampaignConstants.CATEGORY_SEPARATOR +
                miaCards.toSaveString() + CampaignConstants.CATEGORY_SEPARATOR +
                eliminatedCards.toSaveString();
        return Base64.getEncoder().encodeToString(teamString.getBytes());
    }

    public void convertFromString(String teamString, ThingDatabase<Card> db) {
        byte[] decodedBytes = Base64.getDecoder().decode(teamString);
        String decodedString = new String(decodedBytes);
        String[] teamList = decodedString.split(CampaignConstants.CATEGORY_SEPARATOR);
        activeCards.fromSaveString(teamList[0], db);
        tempCards.fromSaveString(teamList[1], db);
        freeAgentCards.fromSaveString(teamList[2], db);
        capturedCards.fromSaveString(teamList[3], db);
        miaCards.fromSaveString(teamList[4], db);
        eliminatedCards.fromSaveString(teamList[5], db);
    }

    public ThingList<Card> getActiveCards() {
        return activeCards;
    }

    public void captureCard(Card card) {
        if(activeCards.contains(card)) {
            capturedCards.add(card);
            activeCards.remove(card);
        }
    }

    public void freeCard(Card card)
    {
        if(capturedCards.contains(card))
        {
            activeCards.add(card);
            capturedCards.remove(card);
        }
    }


    public void eliminateCard(Card card) {

        if(activeCards.contains(card)) {
            eliminatedCards.add(card);
            activeCards.remove(card);
        }
    }

    public void reviveCard(Card card) {
        if(eliminatedCards.contains(card))
        {
            activeCards.add(card);
            eliminatedCards.remove(card);
        }
    }

    public void returnCard(Card card) {
        if(miaCards.contains(card)) {
            activeCards.add(card);
            miaCards.remove(card);
        }
    }


    public void sendAway(Card card) {
        if(activeCards.contains(card)) {
            miaCards.add(card);
            activeCards.remove(card);
        }
    }

    public ThingList<Card> getCaptains() {
        CardList captains = new CardList(new ArrayList<>());
        for(Card c: getActiveCards())
        {
            if(c.isCaptain())
                captains.add(c);
        }
        return captains;
    }
}
