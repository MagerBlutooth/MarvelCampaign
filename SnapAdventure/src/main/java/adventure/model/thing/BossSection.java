package adventure.model.thing;

import adventure.model.AdventureConstants;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.PlayableDatabase;

public class BossSection extends Section {

    public BossSection(Enemy e, PlayableDatabase ct){
        super(0, ct, e);
        advLocation.setID(SnapMainConstants.RUINS_ICON_ID);
    }

    public BossSection(BossSection b)
    {
        super(b);
    }
}
