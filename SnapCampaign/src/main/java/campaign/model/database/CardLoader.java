package campaign.model.database;

import campaign.model.constants.CampaignConstants;
import campaign.model.thing.Thing;

public class CardLoader extends Loader<Thing>{

    public CardLoader() {
        super(CampaignConstants.CARD_FILE);
    }

}
