package snapMain.controller.node;

import snapMain.model.database.MasterThingDatabase;
import snapMain.model.target.BaseObject;

public abstract class CampaignListNodeController<T extends BaseObject>{

    MasterThingDatabase database;

    public void initialize(MasterThingDatabase d) {
        database = d;
    }

}
