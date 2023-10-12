package adventure.model.thing;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Card;
import snapMain.model.target.Location;

public class Mook extends AdvCard {

    public Mook()
    {
        super(new Card());
        setID(SnapMainConstants.MOOK_ICON_ID);
        setEffect("No Enemy Effect.");
    }
}
