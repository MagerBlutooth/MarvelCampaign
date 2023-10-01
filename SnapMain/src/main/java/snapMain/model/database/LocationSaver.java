package snapMain.model.database;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.thing.Location;

public class LocationSaver extends Saver<Location>{

    public LocationSaver(TargetDatabase<Location> database)
    {
        super(CampaignConstants.LOCATION_FILE, database);
    }
}
