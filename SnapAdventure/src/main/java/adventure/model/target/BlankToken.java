package adventure.model.target;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Card;
import snapMain.model.target.Token;

public class BlankToken extends AdvToken {

    public BlankToken()
    {
        super(new Token());
        setID(SnapMainConstants.BLANK_ICON_ID);
        setEffect("No Enemy Effect.");
    }
}
