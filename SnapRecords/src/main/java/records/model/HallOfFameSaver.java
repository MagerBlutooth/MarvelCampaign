package records.model;

import snapMain.model.database.Saver;
import snapMain.model.database.TargetDatabase;

public class HallOfFameSaver extends Saver<HallOfFameEntry>{

    public HallOfFameSaver(String file, TargetDatabase<HallOfFameEntry> database) {
        super(file, database);
    }
}
