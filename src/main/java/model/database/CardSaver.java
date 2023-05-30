package model.database;

import model.constants.CampaignConstants;
import model.thing.Card;

public class CardSaver extends Saver<Card>{

    public CardSaver(ThingDatabase<Card> database)
    {
        super(CampaignConstants.CARD_FILE, database);
    }
}
