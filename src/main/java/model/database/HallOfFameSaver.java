package model.database;

import model.constants.RecordConstants;
import model.thing.HallOfFameEntry;
import model.thing.Thing;

public class HallOfFameSaver extends Saver<HallOfFameEntry> {

    public HallOfFameSaver(ThingDatabase<HallOfFameEntry> database) {
        super(RecordConstants.HOF_FILE, database);
    }

}
