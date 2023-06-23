package model.database;

import model.constants.CampaignConstants;
import model.thing.Token;

public class TokenSaver extends Saver<Token>{

    public TokenSaver(ThingDatabase<Token> database)
    {
        super(CampaignConstants.TOKEN_FILE, database);
    }
}
