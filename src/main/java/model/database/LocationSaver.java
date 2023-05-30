package model.database;

import model.constants.CampaignConstants;
import model.thing.Location;

public class LocationSaver extends Saver<Location>{

    public LocationSaver(ThingDatabase<Location> database)
    {
        super(CampaignConstants.LOCATION_FILE, database);
    }
}
