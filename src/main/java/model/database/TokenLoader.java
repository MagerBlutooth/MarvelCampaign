package model.database;

import model.constants.CampaignConstants;
import model.thing.Token;

public class TokenLoader extends Loader{

    public TokenLoader()
    {
        super(CampaignConstants.TOKEN_FILE);
    }
}
