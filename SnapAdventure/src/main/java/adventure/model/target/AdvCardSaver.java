package adventure.model.target;

import adventure.model.AdventureConstants;
import snapMain.model.database.Saver;
import snapMain.model.database.TargetDatabase;

public class AdvCardSaver extends Saver<AdvCard> {

    public AdvCardSaver(TargetDatabase<AdvCard> database)
    {
        super(AdventureConstants.BOSS_FILE, database);
    }
}
