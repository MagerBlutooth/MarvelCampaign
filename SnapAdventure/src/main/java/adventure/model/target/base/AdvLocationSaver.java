package adventure.model.target.base;

import adventure.model.AdventureConstants;
import snapMain.model.database.Saver;
import snapMain.model.database.TargetDatabase;

public class AdvLocationSaver extends Saver<AdvLocation> {

    public AdvLocationSaver(TargetDatabase<AdvLocation> database)
    {
        super(AdventureConstants.SECTION_FILE, database);
    }
}
