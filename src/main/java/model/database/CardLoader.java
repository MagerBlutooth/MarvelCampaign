package model.database;

import model.constants.CampaignConstants;
import model.thing.Thing;

public class CardLoader extends Loader<Thing>{

    public CardLoader() {
        super(CampaignConstants.CARD_FILE);
    }

}
