package records.model;

import snapMain.model.database.TargetDatabase;

public class RecordSaver {

    public void saveHallOfFame(String profile, TargetDatabase<HallOfFameEntry> entries)
    {

        HallOfFameSaver vSaver = new HallOfFameSaver(profile, entries);
        vSaver.writeCSV();
    }
}
