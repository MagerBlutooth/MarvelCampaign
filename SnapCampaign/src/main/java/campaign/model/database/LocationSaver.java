package campaign.model.database;

import campaign.model.constants.CampaignConstants;
import campaign.model.thing.Location;

public class LocationSaver extends Saver<Location>{

    public LocationSaver(ThingDatabase<Location> database)
    {
        super(CampaignConstants.LOCATION_FILE, database);
    }
}
