package snapMain.model.database;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.BaseObject;

public class CardLoader extends Loader<BaseObject>{

    public CardLoader() {
        super(SnapMainConstants.CARD_FILE);
    }

}
