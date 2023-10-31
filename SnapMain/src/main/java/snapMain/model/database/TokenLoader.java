package snapMain.model.database;

import snapMain.model.constants.SnapMainConstants;

public class TokenLoader extends Loader{

    public TokenLoader()
    {
        super(SnapMainConstants.TOKEN_FILE);
    }
}
