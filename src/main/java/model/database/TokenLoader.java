package model.database;

import model.constants.CampaignConstants;

public class TokenLoader extends Loader{

    public TokenLoader()
    {
        super(CampaignConstants.TOKEN_FILE);
    }
}
