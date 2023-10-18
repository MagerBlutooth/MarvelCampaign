package adventure.model.target;

import adventure.model.AdventureConstants;
import snapMain.model.database.Saver;
import snapMain.model.database.TargetDatabase;

public class AdvTokenSaver extends Saver<AdvToken> {

    public AdvTokenSaver(TargetDatabase<AdvToken> database)
    {
        super(AdventureConstants.ADV_TOKEN_FILE, database);
    }
}
