package adventure.model.target.base;

import adventure.model.target.base.AdvCard;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Card;

public class Mook extends AdvCard {

    public Mook()
    {
        super(new Card());
        setID(SnapMainConstants.MOOK_ICON_ID);
        setEffect("No Enemy Effect.");
    }
}
