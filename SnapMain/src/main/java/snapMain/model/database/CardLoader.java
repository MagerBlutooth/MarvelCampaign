package snapMain.model.database;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.thing.BaseObject;

public class CardLoader extends Loader<BaseObject>{

    public CardLoader() {
        super(CampaignConstants.CARD_FILE);
    }

}
