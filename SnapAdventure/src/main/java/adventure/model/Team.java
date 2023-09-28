package adventure.model;

import campaign.model.constants.CampaignConstants;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.CardList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Team {

    CardList cards;
    CardList tempCards;
    CardList freeAgentCards;
    CardList capturedCards;
    CardList miaCards;
    CardList eliminatedCards;

    public String convertToString() {
        String teamString =  cards.toSaveString() + CampaignConstants.STRING_SEPARATOR +
                tempCards.toSaveString() + CampaignConstants.STRING_SEPARATOR +
                freeAgentCards.toSaveString() + CampaignConstants.STRING_SEPARATOR +
                capturedCards.toSaveString() + CampaignConstants.STRING_SEPARATOR +
                miaCards.toSaveString() + CampaignConstants.STRING_SEPARATOR +
                eliminatedCards.toSaveString();
        return Base64.getEncoder().encodeToString(teamString.getBytes());
    }

    public void convertFromString(String teamString, ThingDatabase<Card> db) {
        byte[] decodedBytes = Base64.getDecoder().decode(teamString);
        String decodedString = new String(decodedBytes);
        String[] teamList = decodedString.split(CampaignConstants.STRING_SEPARATOR);
        cards.fromSaveString(teamList[0], db);
        tempCards.fromSaveString(teamList[1], db);
        freeAgentCards.fromSaveString(teamList[2], db);
        capturedCards.fromSaveString(teamList[3], db);
        miaCards.fromSaveString(teamList[4], db);
        eliminatedCards.fromSaveString(teamList[5], db);
    }
}
