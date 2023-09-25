package campaign.model.thing;

import campaign.model.database.ThingDatabase;

import java.util.ArrayList;
import java.util.Base64;

import static campaign.model.constants.CampaignConstants.STRING_SEPARATOR;
import static campaign.model.constants.CampaignConstants.SUBCATEGORY_SEPARATOR;

public class PlanningInfo {

    int influence;
    CardList activeAgents;

    public PlanningInfo()
    {
        activeAgents = new CardList(new ArrayList<>());
        influence = 0;
    }

    public PlanningInfo(CardList aa, int inf)
    {
        activeAgents = aa;
        influence = inf;
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Card c: activeAgents)
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(STRING_SEPARATOR);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(SUBCATEGORY_SEPARATOR);
        stringBuilder.append(influence);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, ThingDatabase<Card> database)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        String[] catList = decodedString.split(SUBCATEGORY_SEPARATOR);
        String[] cardsList = catList[0].split(STRING_SEPARATOR);

        for(String c: cardsList)
        {
            activeAgents.add(database.lookup(Integer.parseInt(c)));
        }

        influence = Integer.parseInt(catList[1]);

    }

    public CardList getActiveAgents() {
        return activeAgents;
    }

    public int getInfluence() {
        return influence;
    }
}
