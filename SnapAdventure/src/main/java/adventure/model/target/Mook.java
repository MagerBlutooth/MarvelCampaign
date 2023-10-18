package adventure.model.target;

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
