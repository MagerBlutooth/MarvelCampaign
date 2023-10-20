package adventure.model.target.base;

import adventure.model.target.base.AdvLocation;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Location;

public class Ruins extends AdvLocation {

    public Ruins()
    {
        super(new Location());
        setID(SnapMainConstants.RUINS_ICON_ID);
        setEffect("No Location Effect.");
    }
}
