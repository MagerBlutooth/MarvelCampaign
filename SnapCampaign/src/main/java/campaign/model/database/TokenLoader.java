package campaign.model.database;

import campaign.model.constants.CampaignConstants;

public class TokenLoader extends Loader{

    public TokenLoader()
    {
        super(CampaignConstants.TOKEN_FILE);
    }
}