package records.model;

import campaign.model.database.Saver;
import campaign.model.database.ThingDatabase;
import campaign.model.database.ThingSaver;
import campaign.model.thing.Card;

public class HallOfFameSaver extends Saver<HallOfFameEntry>{

    public HallOfFameSaver(String file, ThingDatabase<HallOfFameEntry> database) {
        super(file, database);
    }
}
