package snapMain.model.database;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Token;

public class TokenSaver extends Saver<Token>{

    public TokenSaver(TargetDatabase<Token> database)
    {
        super(SnapMainConstants.TOKEN_FILE, database);
    }
}
