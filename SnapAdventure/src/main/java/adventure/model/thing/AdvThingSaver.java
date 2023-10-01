package adventure.model.thing;

import adventure.model.AdvMasterThingDatabase;
import snapMain.model.database.*;
import snapMain.model.thing.TargetType;

public class AdvThingSaver {

    public void saveBosses(TargetDatabase<AdvCard> advCards)
    {
        AdvCardSaver vSaver = new AdvCardSaver(advCards);
        vSaver.writeCSV();
    }

    public void saveSections(TargetDatabase<AdvLocation> advLocations)
    {
        AdvLocationSaver vSaver = new AdvLocationSaver(advLocations);
        vSaver.writeCSV();
    }

    public void saveDatabase(TargetType type, AdvMasterThingDatabase db) {
        switch(type)
        {
            case CARD:
                saveBosses(db.getBosses());
            case LOCATION:
                saveSections(db.getSections());
        }
    }
}
