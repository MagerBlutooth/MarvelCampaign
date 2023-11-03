package adventure.model.target.base;

import snapMain.model.constants.SnapMainConstants;

public class Ruins extends AdvLocation {

    public Ruins()
    {
        super(new AdvLocation());
        setID(SnapMainConstants.RUINS_ICON_ID);
        setEffect("No Location Effect.");
    }
}
