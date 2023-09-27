package adventure.model;

import campaign.model.database.Saver;
import campaign.model.database.ThingDatabase;

public class BossSaver extends Saver<Boss> {

    public BossSaver(ThingDatabase<Boss> database)
    {
        super(AdventureConstants.BOSS_FILE, database);
    }
}
