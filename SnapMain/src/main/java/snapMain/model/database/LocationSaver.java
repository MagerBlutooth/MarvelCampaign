package snapMain.model.database;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Location;

public class LocationSaver extends Saver<Location>{

    public LocationSaver(TargetDatabase<Location> database)
    {
        super(SnapMainConstants.LOCATION_FILE, database);
    }
}
