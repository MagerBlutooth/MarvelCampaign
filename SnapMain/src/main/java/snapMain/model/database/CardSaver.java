package snapMain.model.database;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.thing.Card;

public class CardSaver extends Saver<Card>{

    public CardSaver(TargetDatabase<Card> database)
    {
        super(CampaignConstants.CARD_FILE, database);
    }
}
