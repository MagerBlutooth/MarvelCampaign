package adventure.model.target.base;

import adventure.model.AdvMasterThingDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.TargetType;

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

    public void saveAdvTokens(TargetDatabase<AdvToken> advTokens)
    {
        AdvTokenSaver vSaver = new AdvTokenSaver(advTokens);
        vSaver.writeCSV();
    }

    public void saveDatabase(TargetType type, AdvMasterThingDatabase db) {
        switch(type)
        {
            case CARD:
                saveBosses(db.getAdvCards());
                break;
            case LOCATION:
                saveSections(db.getAdvLocations());
                break;
            case TOKEN:
                saveAdvTokens(db.getAdvTokens());
                break;
        }
    }
}
