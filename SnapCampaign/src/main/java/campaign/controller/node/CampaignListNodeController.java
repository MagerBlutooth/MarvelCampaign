package campaign.controller.node;

import campaign.model.database.MasterThingDatabase;
import campaign.model.thing.Thing;

public abstract class CampaignListNodeController<T extends Thing>{

    MasterThingDatabase database;

    public void initialize(MasterThingDatabase d) {
        database = d;
    }
}
