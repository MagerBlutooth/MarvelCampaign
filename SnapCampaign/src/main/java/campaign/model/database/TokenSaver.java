package campaign.model.database;

import campaign.model.constants.CampaignConstants;
import campaign.model.thing.Token;

public class TokenSaver extends Saver<Token>{

    public TokenSaver(ThingDatabase<Token> database)
    {
        super(CampaignConstants.TOKEN_FILE, database);
    }
}
