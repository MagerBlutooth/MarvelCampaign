package adventure.model.target.base;

import adventure.model.target.base.AdvToken;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Token;

public class BlankToken extends AdvToken {

    public BlankToken()
    {
        super(new Token());
        setID(SnapMainConstants.BLANK_ICON_ID);
        setEffect("No Enemy Effect.");
    }
}
