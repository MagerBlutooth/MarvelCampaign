package snapMain.model.database;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.target.Token;

public class TokenSaver extends Saver<Token>{

    public TokenSaver(TargetDatabase<Token> database)
    {
        super(CampaignConstants.TOKEN_FILE, database);
    }
}
