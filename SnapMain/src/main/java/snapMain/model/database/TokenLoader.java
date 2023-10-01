package snapMain.model.database;

import snapMain.model.constants.CampaignConstants;

public class TokenLoader extends Loader{

    public TokenLoader()
    {
        super(CampaignConstants.TOKEN_FILE);
    }
}
