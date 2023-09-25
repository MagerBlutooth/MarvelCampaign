package campaign.model.database;

import campaign.model.constants.CampaignConstants;
import campaign.model.thing.Card;

public class CardSaver extends Saver<Card>{

    public CardSaver(ThingDatabase<Card> database)
    {
        super(CampaignConstants.CARD_FILE, database);
    }
}
