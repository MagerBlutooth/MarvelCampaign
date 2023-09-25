package records.model;

import campaign.model.database.ThingDatabase;

import java.io.File;

public class RecordSaver {

    public void saveHallOfFame(String profile, ThingDatabase<HallOfFameEntry> entries)
    {

        HallOfFameSaver vSaver = new HallOfFameSaver(profile, entries);
        vSaver.writeCSV();
    }
}
